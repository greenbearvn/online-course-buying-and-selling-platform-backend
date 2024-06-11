package com.courseplus.questionservice.components;


import com.courseplus.questionservice.entity.Question;
import com.courseplus.questionservice.models.events.ChoiceCreateEvent;
import com.courseplus.questionservice.models.events.TestCreateEvent;
import com.courseplus.questionservice.models.obj.Choice;
import com.courseplus.questionservice.models.res.QuestionRes;
import com.courseplus.questionservice.service.inter.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateQuestionComponent {

    private final ObjectMapper objectMapper;

    private final QuestionService questionService;

    private final KafkaTemplate<String,ChoiceCreateEvent> kafkaTemplate;

    private final String CREATE_CHOICE_TOPIC  = "new-choice";
    @Transactional
    @KafkaListener(topics = "new-test", groupId = "question-group")
    public void createQuestionCom(String event) {
        try {
            TestCreateEvent testCreateEvent = objectMapper.readValue(event, TestCreateEvent.class);

            int testId = testCreateEvent.getTestReq().getTestId();
            List<QuestionRes> questionResList = testCreateEvent.getTestReq().getQuestions();

            for (QuestionRes q : questionResList) {
                Optional<Question> detailQuestion = questionService.getDetailQuestion(q.getQuestionId());
                if (detailQuestion.isEmpty()) {
                    Question question = Question.builder()
                            .testId(testId)
                            .questionDescription(q.getQuestionDescription())
                            .suggestion(q.getSuggestion())
                            .build();

                    question = questionService.createQuestion(question);
                    for (Choice c : q.getChoices()) {
                        c.setQuestionId(question.getQuestionId());
                        ChoiceCreateEvent choiceCreateEvent = new ChoiceCreateEvent();
                        choiceCreateEvent.setChoice(c);
                        kafkaTemplate.send(CREATE_CHOICE_TOPIC, choiceCreateEvent);
                    }
                }
            }

        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace(); // Or log the error
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Or log the error
        }
    }





}
