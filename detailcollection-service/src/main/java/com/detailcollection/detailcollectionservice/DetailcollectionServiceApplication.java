package com.detailcollection.detailcollectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DetailcollectionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetailcollectionServiceApplication.class, args);
    }

}
