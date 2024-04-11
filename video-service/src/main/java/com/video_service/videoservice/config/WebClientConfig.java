package com.video_service.videoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String TEST_SERVICE_URL_BASE= "http://localhost:8092/api/test";

    @Bean
    public WebClient testWebClient() {

        return WebClient.builder().baseUrl(TEST_SERVICE_URL_BASE).build();
    }
}
