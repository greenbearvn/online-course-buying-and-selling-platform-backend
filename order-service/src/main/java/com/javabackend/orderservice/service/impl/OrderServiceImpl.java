package com.javabackend.orderservice.service.impl;
import com.javabackend.orderservice.data.Order;
import com.javabackend.orderservice.data.OrderRepository;
import com.javabackend.orderservice.httpservice.OrderHttpService;
import com.javabackend.orderservice.models.events.OrderEvent;
import com.javabackend.orderservice.models.obj.DetailOrder;
import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.res.CollectionRes;
import com.javabackend.orderservice.models.res.DetailCollectionRes;
import com.javabackend.orderservice.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements  OrderService{

    private final OrderRepository orderRepository;

    private final OrderHttpService orderHttpService;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @Override
    public void createOrder(OrderInfor orderInfor) {
        Order newOrder = Order.builder()
                .userId(orderInfor.getUserId())
                .status(orderInfor.getStatus())
                .createAt(orderInfor.getCreateAt())
                .moneyTotal(orderInfor.getMoneyTotal())
                .build();

        CollectionRes collectionRes = orderHttpService.getCollectionByUserId(orderInfor.getUserId()).block();

        Map<String, DetailOrder> newDetailOrder = new HashMap<String, DetailOrder>();

        orderInfor.getDetailOrder().forEach((key, value) -> {
            DetailCollectionRes detailCollectionRes = orderHttpService.getDetailCollections(collectionRes.getCollectionId(), value.getCourseId()).block();
            if (detailCollectionRes != null) {
                newDetailOrder.put(key, value);
            }
        });


        newOrder = orderRepository.save(newOrder);


        orderInfor.setOrderId(newOrder.getOrderId());

        OrderEvent event = new OrderEvent(); /// Khởi tạo đối tượng event của đơn hàng
        event.setOrder(orderInfor);
        event.setDetailOrder(newDetailOrder);
        event.setType("ORDER_CREATED");
        kafkaTemplate.send("new-order", event);
    }
}
