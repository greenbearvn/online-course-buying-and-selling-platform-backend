package com.javabackend.commentservice.rest.inter;

import com.javabackend.commentservice.models.res.ProfileUserRes;
import com.javabackend.commentservice.models.res.UserRes;
import reactor.core.publisher.Mono;

public interface CommentRestService {

        Mono<ProfileUserRes> getDetailUser(int id);
}
