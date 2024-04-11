package com.lession_service.lessionservice.rest.inter;

import com.lession_service.lessionservice.model.res.VideoRes;
import reactor.core.publisher.Flux;

import java.util.List;

public interface HttpService {

    public Flux<VideoRes> getVideosOfLession(int lessionId);
}
