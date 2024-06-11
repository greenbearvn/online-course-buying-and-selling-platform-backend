package com.courseplus.testservice.rest.impl;

import com.courseplus.testservice.models.res.Profile;
import com.courseplus.testservice.models.res.TestsRes;
import com.courseplus.testservice.rest.inter.ProfileRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProfileRestServiceImpl implements ProfileRestService {

    private final WebClient profileWebClient;
    @Override
    public Mono<Profile> getProfileById(int id) {
        Mono<Profile> profileMono = profileWebClient.get()
                .uri("/detail/" + id)
                .retrieve()
                .bodyToMono(Profile.class);
        return profileMono;
    }
}
