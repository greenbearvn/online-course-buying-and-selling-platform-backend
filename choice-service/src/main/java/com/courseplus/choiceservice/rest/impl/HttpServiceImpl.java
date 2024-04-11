package com.courseplus.choiceservice.rest.impl;

import com.courseplus.choiceservice.model.obj.Question;
import com.courseplus.choiceservice.rest.inter.HttpService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {
    private final WebClient questionWebClient;
    @Override
    @CircuitBreaker(name = "question-service",fallbackMethod = "handleServiceFailure")
    public Mono<Question> getDetailQuestion(int questionId) {
        Mono<Question> detailQuestion = questionWebClient.get()
                .uri("/detail/" + questionId)
                .retrieve()
                .bodyToMono(Question.class);
        return detailQuestion;
    }
    private Question handleServiceFailure(int id, Throwable throwable) {
        return new Question();
    }
}
