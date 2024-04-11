package com.java.detail_order_service.model.events;

import com.java.detail_order_service.entity.DetailOrder;
import com.java.detail_order_service.model.obj.OrderInfor;
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
