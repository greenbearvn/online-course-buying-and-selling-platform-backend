package com.javabackend.orderservice.httpservice;


import com.javabackend.orderservice.models.res.CollectionRes;
import com.javabackend.orderservice.models.res.DetailCollectionRes;
import com.javabackend.orderservice.models.res.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderHttpService {

    private final WebClient collectionWebClient;

    private final WebClient detailCollectionWebClient;

    private final WebClient userWebClient;

    public Mono<CollectionRes> getCollectionByUserId(int userId) {
        Mono<CollectionRes> collectionResFlux = collectionWebClient.get()
                .uri("/user/" + userId)
                .retrieve()
                .bodyToMono(CollectionRes.class);
        return collectionResFlux;
    }

    public Mono<DetailCollectionRes> getDetailCollections(int collectionId, int courseid) {
        Mono<DetailCollectionRes> detailCollectionResMono = detailCollectionWebClient.get()
                .uri("/detail/" + collectionId + "/" + courseid)
                .retrieve()
                .bodyToMono(DetailCollectionRes.class);
        return detailCollectionResMono;
    }

    public Mono<UserRes> getDetailUserById(int userId) {
        Mono<UserRes> detailUser = userWebClient.get()
                .uri("/id/" + userId)
                .retrieve()
                .bodyToMono(UserRes.class);
        return detailUser;
    }

}
