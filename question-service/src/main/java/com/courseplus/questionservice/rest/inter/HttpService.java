package com.courseplus.questionservice.rest.inter;

import com.courseplus.questionservice.models.obj.Choice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HttpService {

    public Flux<Choice> getChoicesByQuestionId(int questionId);


}
