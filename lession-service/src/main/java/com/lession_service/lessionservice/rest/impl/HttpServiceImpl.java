package com.lession_service.lessionservice.rest.impl;

import com.lession_service.lessionservice.model.res.VideoRes;
import com.lession_service.lessionservice.rest.inter.HttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final WebClient videoWebClient;
    @Override
    public Flux<VideoRes> getVideosOfLession(int lessionId) {
        Flux<VideoRes> videoResFlux = videoWebClient.get()
                .uri("/lession/" + lessionId)
                .retrieve()
                .bodyToFlux(VideoRes.class);
        return videoResFlux;
    }
}
