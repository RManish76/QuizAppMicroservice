package com.microservice.quizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//Created QuestionWrapper model to return only specific column
//of question table

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
