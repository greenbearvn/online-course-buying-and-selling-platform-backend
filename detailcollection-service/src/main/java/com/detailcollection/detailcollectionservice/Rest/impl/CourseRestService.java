package com.detailcollection.detailcollectionservice.Rest.impl;

import com.detailcollection.detailcollectionservice.model.obj.CourseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseRestService implements com.detailcollection.detailcollectionservice.Rest.inter.CourseRestService {

    private final WebClient courseWebClient;
    @Override
    public Mono<CourseRes> getDetailCourse(int collectionId) {

        Mono<CourseRes> detailCourse = courseWebClient.get()
                .uri("/detail/" + collectionId)
                .retrieve()
                .bodyToMono(CourseRes.class);
        return detailCourse;
    }
}
