package com.javabackend.testedservice.rest.impl;

import com.javabackend.testedservice.models.res.Profile;
import com.javabackend.testedservice.models.res.Test;
import com.javabackend.testedservice.rest.inter.TestRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TestRestServiceImpl implements TestRestService {

    private final WebClient testWebclient;


    @Override
    public Mono<Test> getDetailTest(int id) {
        Mono<Test> testMono = testWebclient.get()
                .uri("/detail/" + id)
                .retrieve()
                .bodyToMono(Test.class);
        return testMono;
    }
}
