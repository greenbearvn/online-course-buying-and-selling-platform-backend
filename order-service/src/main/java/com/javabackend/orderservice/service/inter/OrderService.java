package com.javabackend.orderservice.service.inter;

import com.javabackend.orderservice.models.req.OrderInfor;

public interface OrderService {

    public void createOrder(OrderInfor orderReq);
}
