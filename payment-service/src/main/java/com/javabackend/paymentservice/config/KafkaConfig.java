package com.javabackend.paymentservice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    private final String REVERSE_CREATE_ORDER = "reversed-orders";

    @Bean
    public NewTopic topic(){

        return TopicBuilder.name(REVERSE_CREATE_ORDER).build();
    }
}
