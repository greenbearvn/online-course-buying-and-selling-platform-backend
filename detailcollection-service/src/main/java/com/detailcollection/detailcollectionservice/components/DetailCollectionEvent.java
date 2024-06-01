package com.detailcollection.detailcollectionservice.components;


import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.model.events.CollectionEvent;
import com.detailcollection.detailcollectionservice.model.obj.Collections;
import com.detailcollection.detailcollectionservice.model.obj.DetailOrder;
import com.detailcollection.detailcollectionservice.service.inter.DetailCollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DetailCollectionEvent {

    private final ObjectMapper objectMapper;

    private final DetailCollectionService detailCollectionService;


    @KafkaListener(topics = "collection-event", groupId = "detail-collection-group")
    public void createDetailOrder(String event) {
        try {
            CollectionEvent collectionEvent = objectMapper.readValue(event, CollectionEvent.class);

            Map<String, DetailOrder> detailOrderMap = collectionEvent.getDetailOrder();
            int collectionId = collectionEvent.getCollections().getCollectionId();

            for (Map.Entry<String, DetailOrder> entry : detailOrderMap.entrySet()) {

                DetailOrder value = entry.getValue();
                List<DetailCollection> detailCollection = detailCollectionService.getDetailCollectionByCollectionIdAndCourseId(collectionId, value.getCourseId());

                if (detailCollection.isEmpty()) {
                    // Create a new detail collection only if it doesn't exist
                    Date date = new Date();
                    DetailCollection newDetailCollection = DetailCollection.builder()
                            .collectionId(collectionId)
                            .courseId(value.getCourseId())
                            .createDate(date)
                            .build();

                    detailCollectionService.createDetailCollection(newDetailCollection);
                }
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
