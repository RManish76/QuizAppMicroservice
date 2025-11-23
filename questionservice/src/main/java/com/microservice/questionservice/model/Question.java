package com.microservice.questionservice.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //JPA automatically convert it the camelCasing to snake_casing as in sql/database we follow snake_casing
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;
    @Column(name = "question_category")
    private String questionCategory;
}
