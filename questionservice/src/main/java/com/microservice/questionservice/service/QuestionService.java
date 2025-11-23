package com.microservice.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.questionservice.dao.QuestionDao;
import com.microservice.questionservice.model.Question;



@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // In case of an exception, return an empty list with an internal server error status.
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllQuestionByCategory(String category) {
        try {
            List<Question> questions = questionDao.findByCategory(category);

            if (questions.isEmpty()) {
                // Return a custom message with 404 Not Found if no data
                return new ResponseEntity<>("Category not found: " + category, HttpStatus.NOT_FOUND);
            }
            
            // Return 200 OK if data exists
            return new ResponseEntity<>(questions, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching questions.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    
}
