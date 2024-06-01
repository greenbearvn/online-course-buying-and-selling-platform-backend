package com.courseplus.choiceservice.components;


import com.courseplus.choiceservice.entity.Choice;
import com.courseplus.choiceservice.model.events.ChoiceCreateEvent;
import com.courseplus.choiceservice.service.inter.ChoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateChoiceComponent {

    private final ObjectMapper objectMapper;

    private final ChoiceService choiceService;
    @Transactional
    @KafkaListener(topics = "new-choice", groupId = "choice-group")
    public void createChoice(String event) {
        try {
            ChoiceCreateEvent choiceCreateEvent = objectMapper.readValue(event, ChoiceCreateEvent.class);

            Choice choice = choiceCreateEvent.getChoice();

            System.out.println(choice);
            choiceService.createChoice(choice);

        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace(); // Or log the error
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Or log the error
        }
    }

}
