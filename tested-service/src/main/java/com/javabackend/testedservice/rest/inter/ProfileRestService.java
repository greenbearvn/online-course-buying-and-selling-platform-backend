package com.javabackend.testedservice.rest.inter;

import com.javabackend.testedservice.models.res.Profile;
import reactor.core.publisher.Mono;

public interface ProfileRestService {

    Mono<Profile> getDetailProfile(int id);
}
