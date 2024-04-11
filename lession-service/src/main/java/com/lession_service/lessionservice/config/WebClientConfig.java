package com.lession_service.lessionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String VIDEO_SERVICE_URL_BASE= "http://localhost:8086/api/v1/videos";

    @Bean
    public WebClient videoWebClient() {

        return WebClient.builder().baseUrl(VIDEO_SERVICE_URL_BASE).build();
    }
}
