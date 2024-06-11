package com.javabackend.testedservice.rest.impl;

import com.javabackend.testedservice.models.res.Profile;
import com.javabackend.testedservice.rest.inter.ProfileRestService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProfileRestServiceImpl implements ProfileRestService {

    private final WebClient profileWebClient;

    @Override
    @CircuitBreaker(name = "profile-service")
    public Mono<Profile> getDetailProfile(int id) {
        Mono<Profile> profileMono = profileWebClient.get()
                .uri("/detail/" + id)
                .retrieve()
                .bodyToMono(Profile.class);
        return profileMono;
    }
}
