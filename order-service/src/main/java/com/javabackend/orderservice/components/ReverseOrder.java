package com.javabackend.orderservice.components;


import com.javabackend.orderservice.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ReverseOrder {

    private  final OrderRepository orderRepository;

//    @KafkaListener(topics = "reversed-order", groupId = "orders-group")
//    public void reverseOrder(String event) {
//        System.out.println("Inside reverse order for order "+event);
//
//        try {
//            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
//
//            // tìm đơn hàng theo mã
//            Optional<Order> order = orderRepository.findById(orderEvent.getOrderDetail().getOrderId());
//
//            order.ifPresent(o -> {
//                o.setStatus("fail");
//                this.orderRepository.save(o);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
