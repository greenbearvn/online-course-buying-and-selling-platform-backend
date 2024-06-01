package com.category_service.categoryservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class WebClientConfig {

    private final String DETAIL_CATEGORY_SERVICE_NAME = "http://localhost:8083/api/detailcates";


    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(DETAIL_CATEGORY_SERVICE_NAME).build();
    }


}
