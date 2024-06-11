package com.collection_service.collectionservice.rest.impl;

import com.collection_service.collectionservice.model.res.UserRes;
import com.collection_service.collectionservice.rest.inter.UserRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserRestServiceImpl  implements UserRestService {

    private final WebClient userWebClient;

    public Mono<UserRes> getDetailUserById(int userId) {
        Mono<UserRes> detailUser = userWebClient.get()
                .uri("/id/" + userId)
                .retrieve()
                .bodyToMono(UserRes.class);
        return detailUser;
    }
}
