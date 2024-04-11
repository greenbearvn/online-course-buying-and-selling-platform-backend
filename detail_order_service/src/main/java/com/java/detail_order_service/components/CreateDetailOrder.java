package com.java.detail_order_service.components;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.detail_order_service.entity.DetailOrder;
import com.java.detail_order_service.model.events.OrderEvent;
import com.java.detail_order_service.model.obj.OrderInfor;
import com.java.detail_order_service.service.inter.DetailOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateDetailOrder {

    private final DetailOrderService detailOrderService;
    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "new-order", groupId = "detail-order-group")
    public void createCollection(String event) {
        try {
            OrderEvent orderEvent = objectMapper.readValue(event, OrderEvent.class);

            Map<String, DetailOrder> detailOrderMap = orderEvent.getDetailOrder();

            detailOrderMap.forEach((key, value) -> detailOrderService.createDetailOrder(value));

        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace(); // Or log the error
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Or log the error
        }
    }
}
