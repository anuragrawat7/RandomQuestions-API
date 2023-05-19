package com.restapi.RandomQuestions.controller;

import com.restapi.RandomQuestions.dto.NextQuestionResponseDto;
import com.restapi.RandomQuestions.dto.QuestionAnswerRequestDto;
import com.restapi.RandomQuestions.entities.Questions;
import com.restapi.RandomQuestions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class RandomController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchAllQuestions() throws NoSuchFieldException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jservice.io/api/random";
        List<Questions> questionsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var response = restTemplate.getForEntity(url, Questions[].class);
            Questions data = Objects.requireNonNull(response.getBody())[0];
            questionsList.add(data);
        }
        return questionService.saveQuestions(questionsList);
    }

    @GetMapping("/play")
    public ResponseEntity<?> getRandomQuestionHandler(){
        return questionService.getRandomQuestion();
    }

    @PostMapping("/next")
    public ResponseEntity<NextQuestionResponseDto> nextQuestionHandler(@RequestBody QuestionAnswerRequestDto questionAnswerRequestDto) throws Exception {
        return questionService.getNextQuestion(questionAnswerRequestDto);
    }

}
