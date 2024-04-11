package com.collection_service.collectionservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    private final String TOPIC = "collection-event";

    @Bean
    public NewTopic createCollectionTopic(){

        return TopicBuilder.name(TOPIC).build();
    }
}
