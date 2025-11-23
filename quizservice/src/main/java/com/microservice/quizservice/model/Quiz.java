package com.microservice.quizservice.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany
    //@ManyToMany annotation will create anthore table to store the question id and quiz id
    // and will used that to fetch List of quesions automatically
    private List<Question> questions;
}
