package com.javabackend.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final String COLLECTION_SERVICE_URL_BASE= "http://localhost:8087/api/v1/collections";
    private final String DETAIL_COLLECTION_SERVICE_URL_BASE= "http://localhost:8091/api/v1/detailcollection";

    private final String USER_SERVICE_URL_BASE= "http://localhost:8099/api/user";

    @Bean
    public WebClient collectionWebClient() {

        return WebClient.builder().baseUrl(COLLECTION_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient detailCollectionWebClient() {

        return WebClient.builder().baseUrl(DETAIL_COLLECTION_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient userWebClient() {

        return WebClient.builder().baseUrl(USER_SERVICE_URL_BASE).build();
    }
}
