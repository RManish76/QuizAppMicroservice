package com.microservice.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.quizservice.dao.QuizDao;
import com.microservice.quizservice.model.QuestionWrapper;



@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //call the generate url of question microservice - using RestTemplate of SpringFramwork
        //generate url = http://localhost:8080/question/generate  (bad not recomended hardcoding)
        //use OpenFiegn - helps in articulating the rest requests/response
        //use Service Discovery Client - to find the rest of other service and link it
        //                        every microservice rest will be registed in Discovery client
        //                        we'll use Eureka which from netflix for this which will be another spingboot project

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        
        for(Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<UserResponse> userResponse) {
        Quiz quiz = quizDao.findById(id).get();
        java.util.Map<Integer, Question> questionMap = quiz.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int right = 0;
        for(UserResponse ur : userResponse){
            if(questionMap.containsKey(ur.getId()) && ur.getResponse().equals(questionMap.get(ur.getId()).getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
