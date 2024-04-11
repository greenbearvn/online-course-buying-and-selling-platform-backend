package com.courseplus.testservice.rest.inter;

import com.courseplus.testservice.models.res.QuestionRes;
import reactor.core.publisher.Flux;

public interface HttpService {

    public Flux<QuestionRes> getQuestionsByTestId(int testId);
}
