package com.javabackend.orderservice.controller;

import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderInfor orderReq) {

        orderService.createOrder(orderReq);
    }


}
