package com.microservice.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.questionservice.model.Question;
import com.microservice.questionservice.model.QuestionWrapper;
import com.microservice.questionservice.model.UserResponse;
import com.microservice.questionservice.service.QuestionService;
import org.springframework.core.env.Environment;



@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment; //use to get details of enviorment such as port number of instance

    
    // @Autowired
    // public void setQuestionService(QuestionService questionService) {
    //     this.questionService = questionService;
    // }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<Object> getAllQuestionByCategory(@PathVariable("category") String category){
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    //Quiz service will request to generate the quiz using below rest call
    //below call will return numQ random question within the requested category
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam int numQ){
        return questionService.getQuestionsForQuiz(categoryName, numQ);
    }

    @PostMapping("getQuestions")
    //below controller for quiz, so that it can request question as per questionid
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port")); //will print the port when request made, and we'll able to see loadbalancing
        return questionService.getQuestionsFromId(questionIds);
    }

    //as quiz does not have the question nor the answer so it has to request question microservice
    //to calculate the score of submission 

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> responses){
        return questionService.getScore(responses);
    }

}
