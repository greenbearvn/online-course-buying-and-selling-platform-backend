package com.javabackend.testedservice.rest.inter;

import com.javabackend.testedservice.models.res.Test;
import reactor.core.publisher.Mono;

public interface TestRestService {

    Mono<Test> getDetailTest(int id);
}
