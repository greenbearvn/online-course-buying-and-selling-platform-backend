package com.courseplus.testservice.rest.inter;

import com.courseplus.testservice.models.res.Profile;
import com.courseplus.testservice.models.res.TestsRes;
import reactor.core.publisher.Mono;

public interface ProfileRestService {

    Mono<Profile> getProfileById(int id);
}
