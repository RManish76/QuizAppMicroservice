package com.microservice.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    //DTO - Data Transfer Object
    private String categoryName;
    private Integer numQuestions;
    private String title;
}
