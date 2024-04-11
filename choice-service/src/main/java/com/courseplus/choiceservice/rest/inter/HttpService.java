package com.courseplus.choiceservice.rest.inter;

import com.courseplus.choiceservice.model.obj.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HttpService {

    public Mono<Question> getDetailQuestion(int questionId);
}
