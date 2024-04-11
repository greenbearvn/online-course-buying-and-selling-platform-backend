package com.courseplus.questionservice.rest.impl;

import com.courseplus.questionservice.models.obj.Choice;
import com.courseplus.questionservice.rest.inter.HttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {
    private final WebClient choiceWebClient;
    @Override
    public Flux<Choice> getChoicesByQuestionId(int questionId) {
        Flux<Choice> choiceFlux = choiceWebClient.get()
                .uri("/list/question/" + questionId)
                .retrieve()
                .bodyToFlux(Choice.class);
        return choiceFlux;
    }



}
