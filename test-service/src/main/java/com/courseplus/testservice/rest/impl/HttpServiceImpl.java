package com.courseplus.testservice.rest.impl;

import com.courseplus.testservice.models.res.QuestionRes;
import com.courseplus.testservice.rest.inter.HttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private  final WebClient questionWebClient;
    @Override
    public Flux<QuestionRes> getQuestionsByTestId(int testId) {
        Flux<QuestionRes> questionResFlux = questionWebClient.get()
                .uri("/list/test/" + testId)
                .retrieve()
                .bodyToFlux(QuestionRes.class);
        return questionResFlux;
    }
}
