package com.courseplus.userservice.rest.inter;

import com.courseplus.userservice.models.res.Profile;
import reactor.core.publisher.Mono;

public interface ProfileRestService {

    Mono<Profile> getDetailProfileOfUser(int id);
}
