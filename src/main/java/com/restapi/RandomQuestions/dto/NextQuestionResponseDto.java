package com.restapi.RandomQuestions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NextQuestionResponseDto {

    private String answer;

    private QuestionsResponseDto questionsResponseDto;
}

