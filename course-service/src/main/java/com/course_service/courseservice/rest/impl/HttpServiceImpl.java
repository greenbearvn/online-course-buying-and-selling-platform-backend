package com.course_service.courseservice.rest.impl;

import com.course_service.courseservice.models.obj.DetailCate;
import com.course_service.courseservice.models.obj.Levels;
import com.course_service.courseservice.models.obj.Profile;
import com.course_service.courseservice.models.res.CourseRes;
import com.course_service.courseservice.models.res.LessionRes;
import com.course_service.courseservice.rest.inter.HttpService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final WebClient lessionWebClient;

    private final WebClient detailCateWebClient;

    private final WebClient levelWebClient;

    private final WebClient profileWebClient;
    @Override
    @CircuitBreaker(name = "lession-service")
    public Flux<LessionRes> getLessions(int courseId) {
        Flux<LessionRes> lessionResFlux = lessionWebClient.get()
                .uri("/course/" + courseId)
                .retrieve()
                .bodyToFlux(LessionRes.class);
        return lessionResFlux;
    }

    private Flux<LessionRes> handleNotFoundLessionService(Throwable throwable) {

        return Flux.error(new Exception());
    }


    @Override
    @CircuitBreaker(name = "detailcate-service")
    public Mono<DetailCate> getDetailCate(int dcId) {
        Mono<DetailCate> detailCateMono = detailCateWebClient.get()
                .uri("/detail/" + dcId)
                .retrieve()
                .bodyToMono(DetailCate.class);
        return detailCateMono;
    }

    @Override
    @CircuitBreaker(name = "level-service")
    public Mono<Levels> getLevelOfCourse(int idLevel) {
        Mono<Levels> levelsMono = levelWebClient.get()
                .uri("/detail/" + idLevel)
                .retrieve()
                .bodyToMono(Levels.class);
        return levelsMono;
    }

    @Override
    @CircuitBreaker(name = "profile-service")
    public Mono<Profile> getDetailTeacherOfCourse(int teacherId) {
        Mono<Profile> profileMono = profileWebClient.get()
                .uri("/detail/" + teacherId)
                .retrieve()
                .bodyToMono(Profile.class);
        return profileMono;
    }
}
