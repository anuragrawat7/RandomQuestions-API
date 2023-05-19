package com.restapi.RandomQuestions.repository;

import com.restapi.RandomQuestions.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
}
