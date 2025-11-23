package com.microservice.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.quizservice.model.Quiz;





public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
