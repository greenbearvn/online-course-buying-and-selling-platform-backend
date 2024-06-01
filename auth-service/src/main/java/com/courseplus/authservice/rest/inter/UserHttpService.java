package com.courseplus.authservice.rest.inter;

import com.courseplus.authservice.model.obj.User;
import reactor.core.publisher.Mono;

public interface UserHttpService {

    public Mono<User> findUserByEmail(String email);
}
