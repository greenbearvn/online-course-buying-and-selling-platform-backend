package com.courseplus.questionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCientConfig {

    private final String CHOICE_SERVICE_URL_BASE= "http://localhost:8094/api/v1/choice";

    @Bean
    public WebClient choiceWebClient() {

        return WebClient.builder().baseUrl(CHOICE_SERVICE_URL_BASE).build();
    }
}
