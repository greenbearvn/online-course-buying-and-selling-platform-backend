package com.collection_service.collectionservice.rest.inter;


import com.collection_service.collectionservice.model.res.UserRes;
import reactor.core.publisher.Mono;

public interface UserRestService {


    public Mono<UserRes> getDetailUserById(int userId);

}
