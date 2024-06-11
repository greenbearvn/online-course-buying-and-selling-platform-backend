package com.java.detail_order_service.controller;

import com.java.detail_order_service.model.res.DetailOrderRes;
import com.java.detail_order_service.service.inter.DetailOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detail-order")
@RequiredArgsConstructor
public class DetailOrderController {

    private final DetailOrderService detailOrderService;

    @GetMapping("/list/order/{id}")
    public ResponseEntity<List<DetailOrderRes>> getAllDetailOrderByOrder(@PathVariable int id) {

        List<DetailOrderRes> detailOrderRes = detailOrderService.listDetailOrderByOrderId(id);
        return ResponseEntity.ok().body(detailOrderRes);

    }
}
