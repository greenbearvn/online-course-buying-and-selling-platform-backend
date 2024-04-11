package com.collection_service.collectionservice.components;

import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.model.events.CollectionEvent;
import com.collection_service.collectionservice.model.events.OrderEvent;
import com.collection_service.collectionservice.model.obj.DetailOrder;
import com.collection_service.collectionservice.model.obj.OrderInfor;
import com.collection_service.collectionservice.repository.CollectionRepository;
import com.collection_service.collectionservice.service.inter.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateCollection {

    private final CollectionService collectionService;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, CollectionEvent> kafkaTemplate;

    @KafkaListener(topics = "new-order", groupId = "collection-group")
    public void AddToCollection(String event) {
        try {

            OrderEvent orderEvent = objectMapper.readValue(event, OrderEvent.class);

            OrderInfor orderInfor = orderEvent.getOrder();

            Map<String, DetailOrder> detailOrderMap = orderEvent.getDetailOrder();

            Optional<Collections> collections = Optional.ofNullable(collectionService.getCollectionByUserId(orderInfor.getUserId()));


            if (collections.isEmpty()) {
                Collections newCollection = Collections.builder()
                        .userId(orderInfor.getUserId())
                        .build();
                newCollection = collectionService.createCollection(newCollection);

                newCollection.setCollectionId(newCollection.getCollectionId());

                CollectionEvent collectionEvent = new CollectionEvent();

                collectionEvent.setCollections(newCollection);
                collectionEvent.setDetailOrder(detailOrderMap);
                collectionEvent.setType("collection_create");

                kafkaTemplate.send("collection-event", collectionEvent);
            }

            if(collections.isPresent()){
                CollectionEvent collectionEvent = new CollectionEvent();

                collectionEvent.setCollections(collections.get());
                collectionEvent.setDetailOrder(detailOrderMap);
                collectionEvent.setType("collection_update");

                kafkaTemplate.send("collection-event", collectionEvent);
            }

        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace(); // Or log the error
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Or log the error
        }
    }
}
