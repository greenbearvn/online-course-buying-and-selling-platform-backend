package com.javabackend.testedservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    private final String TEACHER_SERVICE_URL_BASE= "http://localhost:8095/api/profile";

    private final String TEST_SERVICE_URL_BASE= "http://localhost:8092/api/test";

    @Bean
    public WebClient profileWebClient() {

        return WebClient.builder().baseUrl(TEACHER_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient testWebclient() {

        return WebClient.builder().baseUrl(TEST_SERVICE_URL_BASE).build();
    }
}
