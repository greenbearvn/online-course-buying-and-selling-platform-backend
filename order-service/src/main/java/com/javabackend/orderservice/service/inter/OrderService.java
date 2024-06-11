package com.javabackend.orderservice.service.inter;

import com.javabackend.orderservice.data.Order;
import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.res.OrderRes;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    public void createOrder(OrderInfor orderReq);

    public List<OrderRes> getAllOrders();

    public boolean delete(int id);

    public Order detail(int id);

    public Order update(int id, Order order);
}
