package com.courseplus.userservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String TEACHER_SERVICE_URL_BASE= "http://localhost:8095/api/profile";

    @Bean
    public WebClient profileWebClient() {

        return WebClient.builder().baseUrl(TEACHER_SERVICE_URL_BASE).build();
    }
}
