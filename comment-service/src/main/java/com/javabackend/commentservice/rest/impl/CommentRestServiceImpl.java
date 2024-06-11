package com.javabackend.commentservice.rest.impl;

import com.javabackend.commentservice.models.res.ProfileUserRes;
import com.javabackend.commentservice.models.res.UserRes;
import com.javabackend.commentservice.rest.inter.CommentRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CommentRestServiceImpl implements CommentRestService {
    private final WebClient userWebClient;
    @Override
    public Mono<ProfileUserRes> getDetailUser(int id) {
        Mono<ProfileUserRes> user = userWebClient.get()
                .uri("/profile/" + id)
                .retrieve()
                .bodyToMono(ProfileUserRes.class);
        return user;
    }
}
