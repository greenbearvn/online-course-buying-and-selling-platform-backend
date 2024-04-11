package com.course_service.courseservice.rest.inter;

import com.course_service.courseservice.models.obj.DetailCate;
import com.course_service.courseservice.models.obj.Levels;
import com.course_service.courseservice.models.obj.Profile;
import com.course_service.courseservice.models.res.LessionRes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HttpService {

    public Flux<LessionRes> getLessions(int courseId);

    public Mono<DetailCate> getDetailCate(int dcId);

    public Mono<Levels> getLevelOfCourse(int idLevel);

    public Mono<Profile> getDetailTeacherOfCourse(int teacherId);

}
