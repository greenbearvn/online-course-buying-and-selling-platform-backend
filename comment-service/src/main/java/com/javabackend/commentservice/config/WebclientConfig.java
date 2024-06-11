package com.javabackend.commentservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    private final String USER_SERVICE_URL_BASE= "http://localhost:8099/api/user";

    @Bean
    public WebClient userWebClient() {

        return WebClient.builder().baseUrl(USER_SERVICE_URL_BASE).build();
    }
}
