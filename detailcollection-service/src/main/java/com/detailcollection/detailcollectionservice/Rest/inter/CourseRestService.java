package com.detailcollection.detailcollectionservice.Rest.inter;

import com.detailcollection.detailcollectionservice.model.obj.CourseRes;
import reactor.core.publisher.Mono;

public interface CourseRestService {

    public Mono<CourseRes> getDetailCourse(int collectionId);

}
