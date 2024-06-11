package com.courseplus.testservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final String QUESTION_SERVICE_URL_BASE= "http://localhost:8093/api/v1/question";

    private final String TEACHER_SERVICE_URL_BASE= "http://localhost:8095/api/profile";

    @Bean
    public WebClient profileWebClient() {

        return WebClient.builder().baseUrl(TEACHER_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient questionWebClient() {

        return WebClient.builder().baseUrl(QUESTION_SERVICE_URL_BASE).build();
    }
}
