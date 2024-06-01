package com.courseplus.testservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopic {

    private final String TEST_CREATE = "new-test";

    @Bean
    public NewTopic testCreate(){

        return TopicBuilder.name(TEST_CREATE).build();
    }
}
