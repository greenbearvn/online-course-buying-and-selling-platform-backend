package com.java.detail_order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final String COURSE_SERVICE_URL_BASE= "http://localhost:8084/api/courses";

    @Bean
    public WebClient courseWebClient() {

        return WebClient.builder().baseUrl(COURSE_SERVICE_URL_BASE).build();
    }
}
