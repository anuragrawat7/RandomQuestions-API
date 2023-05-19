package com.restapi.RandomQuestions.service;
import com.restapi.RandomQuestions.dto.NextQuestionResponseDto;
import com.restapi.RandomQuestions.dto.QuestionAnswerRequestDto;
import com.restapi.RandomQuestions.dto.QuestionsResponseDto;
import com.restapi.RandomQuestions.entities.Questions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    public ResponseEntity<?> saveQuestions(List<Questions> questionsList) throws NoSuchFieldException;

    public ResponseEntity<QuestionsResponseDto> getRandomQuestion();

    public ResponseEntity<NextQuestionResponseDto> getNextQuestion(QuestionAnswerRequestDto questionAnswerRequestDto) throws Exception;
}
