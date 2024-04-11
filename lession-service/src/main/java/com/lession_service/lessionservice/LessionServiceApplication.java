package com.lession_service.lessionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LessionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessionServiceApplication.class, args);
    }

}
