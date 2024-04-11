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

    private final String DETAIL_CATEGORY_SERVICE_NAME = "http://localhost:8083/detailcates";


    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public WebClient webClient() {
//        String detailCategoryUrl = getServiceUrl(DETAIL_CATEGORY_SERVICE_NAME);
        return WebClient.builder().baseUrl(DETAIL_CATEGORY_SERVICE_NAME).build();
    }

//    private String getServiceUrl(String serviceName) {
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
//        if (instances.isEmpty()) {
//            throw new IllegalStateException("No instances available for service: " + serviceName);
//        }
//        ServiceInstance serviceInstance = instances.get(0); // Choose the first instance
//
//        String serviceUrl = serviceInstance.getUri().toString();
//        return serviceUrl;
//    }

}
