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
import com.microservice.quizservice.fiegn.QuizInterface;
import com.microservice.quizservice.model.QuestionWrapper;
import com.microservice.quizservice.model.Quiz;
import com.microservice.quizservice.model.UserResponse;



@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    
    
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer>questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionId(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIdFromDB = quiz.get().getQuestionId();
        ResponseEntity<List<QuestionWrapper>> questionsForUser = quizInterface.getQuestionsFromId(questionIdFromDB);
        
        // for(Question q : questionFromDB){
        //     QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
        //     questionsForUser.add(qw);
        // }


        return questionsForUser;
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<UserResponse> userResponse) {
        ResponseEntity<Integer> score = quizInterface.getScore(userResponse);
        return score;
    }
}
