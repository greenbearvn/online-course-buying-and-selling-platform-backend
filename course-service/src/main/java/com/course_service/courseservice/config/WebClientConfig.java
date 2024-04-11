package com.course_service.courseservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String LESSION_SERVICE_URL_BASE= "http://localhost:8085/api/v1/lessions";

    private final String DETAIL_CATE_SERVICE_URL_BASE= "http://localhost:8083/api/detailcates";

    private final String LEVEL_SERVICE_URL_BASE= "http://localhost:8081/api/v1/levels";

    private final String TEACHER_SERVICE_URL_BASE= "http://localhost:8095/api/profile";

    @Bean
    public WebClient lessionWebClient() {

        return WebClient.builder().baseUrl(LESSION_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient detailCateWebClient() {

        return WebClient.builder().baseUrl(DETAIL_CATE_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient levelWebClient() {

        return WebClient.builder().baseUrl(LEVEL_SERVICE_URL_BASE).build();
    }

    @Bean
    public WebClient profileWebClient() {

        return WebClient.builder().baseUrl(TEACHER_SERVICE_URL_BASE).build();
    }
}
