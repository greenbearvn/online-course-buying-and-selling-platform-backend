package com.courseplus.userservice.rest.impl;

import com.courseplus.userservice.models.res.Profile;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProfileRestService implements com.courseplus.userservice.rest.inter.ProfileRestService {

    private final WebClient profileWebClient;

    @Override
    @CircuitBreaker(name = "profile-service")
    public Mono<Profile> getDetailProfileOfUser(int teacherId) {
        Mono<Profile> profileMono = profileWebClient.get()
                .uri("/detail/" + teacherId)
                .retrieve()
                .bodyToMono(Profile.class);
        return profileMono;
    }
}
