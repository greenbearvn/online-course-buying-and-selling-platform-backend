package com.javabackend.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().build();
//                .route("level-service", r -> r.path("/api/v1/levels/**")
//                        .filters(f -> f.filter(filter))
//                        .uri("http://localhost:8081/"))
//                .route("user-service", r -> r.path("/api/user/**")
//                        .filters(f -> f.filter(filter))
//                        .uri("http://localhost:8099/"))
//                .route("auth-service", r -> r.path("/api/auth/**")
//                        .filters(f -> f.filter(filter))
//                        .uri("http://localhost:8100/"))
//                .build();

    }
}
