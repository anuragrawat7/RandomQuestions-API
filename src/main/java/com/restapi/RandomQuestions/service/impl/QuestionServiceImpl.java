package com.restapi.RandomQuestions.service.impl;

import com.restapi.RandomQuestions.dto.NextQuestionResponseDto;
import com.restapi.RandomQuestions.dto.QuestionAnswerRequestDto;
import com.restapi.RandomQuestions.dto.QuestionsResponseDto;
import com.restapi.RandomQuestions.entities.Questions;
import com.restapi.RandomQuestions.repository.QuestionsRepository;
import com.restapi.RandomQuestions.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private Questions questions;

    private final EntityManager entityManager;

    @Override
    public ResponseEntity<?> saveQuestions(List<Questions> questionsList) {

        for (int i = 0; i < questionsList.size(); i++) {
            System.out.println("--- " + questionsList.get(i).getAirdate());
            questions.setId(questionsList.get(i).getId());
            questions.setAnswer(questionsList.get(i).getAnswer());
            questions.setQuestion(questionsList.get(i).getQuestion());
            questions.setValue(questionsList.get(i).getValue());
            questions.setAirdate(questionsList.get(i).getAirdate());
            questions.setCreated_at(questionsList.get(i).getCreated_at());
            questions.setUpdated_at(questionsList.get(i).getUpdated_at());
            questions.setCategory_id(questionsList.get(i).getCategory_id());
            questions.setGame_id(questionsList.get(i).getGame_id());
            questions.setInvalid_count(questionsList.get(i).getInvalid_count());
            questions.setCategory(questionsList.get(i).getCategory());
            questionsRepository.save(questions);
        }
        return ResponseEntity.ok("All fetched data is stored in the database");
    }

    @Override
    public ResponseEntity<QuestionsResponseDto> getRandomQuestion() {
        QuestionsResponseDto questionsResponseDto = new QuestionsResponseDto();
        var s = entityManager.createNativeQuery("Select id, question from random_Questions order by rand() limit 1");
        List<Object[]> data = s.getResultList();
        for (Object[] obj: data){
            questionsResponseDto.setId((Integer) obj[0]);
            questionsResponseDto.setQuestion((String) obj[1]);
        }
        return ResponseEntity.ok(questionsResponseDto);
    }

    @Override
    public ResponseEntity<NextQuestionResponseDto> getNextQuestion(QuestionAnswerRequestDto questionAnswerRequestDto) throws Exception {
        NextQuestionResponseDto nextQuestionResponseDto = new NextQuestionResponseDto();
        final var questionData = questionsRepository.findById(questionAnswerRequestDto.getId()).orElseThrow(() -> new Exception("Question not found"));
        nextQuestionResponseDto.setAnswer(questionData.getAnswer());
        QuestionsResponseDto questionsResponseDto = getRandomQuestion().getBody();
        nextQuestionResponseDto.setQuestionsResponseDto(questionsResponseDto);
        return ResponseEntity.ok(nextQuestionResponseDto);
    }

}
