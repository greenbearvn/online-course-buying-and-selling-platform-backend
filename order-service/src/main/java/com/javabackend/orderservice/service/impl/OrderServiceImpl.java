package com.javabackend.orderservice.service.impl;
import com.javabackend.orderservice.data.Order;
import com.javabackend.orderservice.data.OrderRepository;
import com.javabackend.orderservice.httpservice.OrderHttpService;
import com.javabackend.orderservice.models.events.OrderEvent;
import com.javabackend.orderservice.models.obj.DetailOrder;
import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.res.CollectionRes;
import com.javabackend.orderservice.models.res.DetailCollectionRes;
import com.javabackend.orderservice.models.res.OrderRes;
import com.javabackend.orderservice.models.res.UserRes;
import com.javabackend.orderservice.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, DetailOrder> newDetailOrder = new HashMap<String, DetailOrder>();
        CollectionRes collectionRes = orderHttpService.getCollectionByUserId(orderInfor.getUserId()).block();

//      if(collectionRes != null){
//          orderInfor.getDetailOrder().forEach((key, value) -> {
//              DetailCollectionRes detailCollectionRes = orderHttpService.getDetailCollections(collectionRes.getCollectionId(), value.getCourseId()).block();
//              if (detailCollectionRes != null) {
//                  newDetailOrder.put(key, value);
//              }
//          });
//      }

        newOrder = orderRepository.save(newOrder);
        orderInfor.setOrderId(newOrder.getOrderId());

        OrderEvent event = new OrderEvent(); /// Khởi tạo đối tượng event của đơn hàng
        event.setOrder(orderInfor);
        event.setDetailOrder(orderInfor.getDetailOrder());
        event.setType("ORDER_CREATED");
        kafkaTemplate.send("new-order", event);
    }

    @Override
    public List<OrderRes> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderRes> allOrderRes = orders.stream().map(c ->{
            UserRes userRes = orderHttpService.getDetailUserById(c.getUserId()).block();


            return OrderRes.orderResBuilder(c,userRes);
        }).collect(Collectors.toList());

        return allOrderRes;
    }

    @Override
    public boolean delete(int id) {
        try{
            orderRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Order detail(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order update(int id, Order orderReq) {

        Order order = new Order();

        order.setOrderId(id);
        order.setUserId(orderReq.getUserId());
        order.setStatus(orderReq.getStatus());
        order.setCreateAt(orderReq.getCreateAt());
        order.setMoneyTotal(orderReq.getMoneyTotal());

        orderRepository.save(order);
        return null;
    }
}
