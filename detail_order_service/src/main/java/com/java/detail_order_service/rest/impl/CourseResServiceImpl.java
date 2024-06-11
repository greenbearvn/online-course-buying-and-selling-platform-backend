package com.java.detail_order_service.rest.impl;

import com.java.detail_order_service.model.res.CourseRes;
import com.java.detail_order_service.rest.inter.CourseResService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CourseResServiceImpl implements CourseResService {

    private final WebClient courseWebClient;
    @Override
    public Mono<CourseRes> getDetailCourse(int id) {
        Mono<CourseRes> detailCourse = courseWebClient.get()
                .uri("/detail/" + id)
                .retrieve()
                .bodyToMono(CourseRes.class);
        return detailCourse;
    }
}
