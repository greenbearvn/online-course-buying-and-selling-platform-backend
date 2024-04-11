package com.video_service.videoservice.rest.inter;

import com.video_service.videoservice.model.obj.Test;
import reactor.core.publisher.Flux;

import java.util.List;

public interface HttpService {

    public Flux<Test> getAllTestOfVideo(int videoId);
}
