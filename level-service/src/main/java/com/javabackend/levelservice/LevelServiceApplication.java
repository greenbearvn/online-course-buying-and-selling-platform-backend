package com.javabackend.levelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LevelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelServiceApplication.class, args);
	}

}
