package com.javabackend.orderservice.models.events;


import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.obj.DetailOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {

    private String type;
    private OrderInfor order;

    Map<String, DetailOrder> detailOrder;
}
