package com.javabackend.paymentservice.models.events;


import com.javabackend.paymentservice.models.dto.OrderDetail;
import lombok.Data;

@Data
public class OrderEvent {
    private String type;

    private OrderDetail orderDetail;
}
