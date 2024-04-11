package com.video_service.videoservice.rest.impl;

import com.video_service.videoservice.model.obj.Test;
import com.video_service.videoservice.rest.inter.HttpService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final WebClient testWebClient;
    @Override
    @CircuitBreaker(name = "video-service")
    public Flux<Test> getAllTestOfVideo(int videoId) {
        Flux<Test> testFlux = testWebClient.get()
                .uri("/list/video/" + videoId)
                .retrieve()
                .bodyToFlux(Test.class);
        return testFlux;
    }
}
