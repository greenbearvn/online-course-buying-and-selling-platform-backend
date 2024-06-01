package com.courseplus.authservice.rest.impl;

import com.courseplus.authservice.model.obj.User;
import com.courseplus.authservice.rest.inter.UserHttpService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserHttpServiceImpl implements UserHttpService {

    private final WebClient userWebClient;
    @Override
    @CircuitBreaker(name = "user-service")
    public Mono<User> findUserByEmail(String email) {
        Mono<User> userMono = userWebClient.get()
                .uri("/email/" + email)
                .retrieve()
                .bodyToMono(User.class);
        return userMono;
    }
}
