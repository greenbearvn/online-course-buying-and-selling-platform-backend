package com.java.detail_order_service.rest.inter;

import com.java.detail_order_service.model.res.CourseRes;
import reactor.core.publisher.Mono;

public interface CourseResService {


    public Mono<CourseRes> getDetailCourse(int id);
}
