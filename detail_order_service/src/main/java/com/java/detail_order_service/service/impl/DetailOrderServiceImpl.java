package com.java.detail_order_service.service.impl;

import com.java.detail_order_service.entity.DetailOrder;
import com.java.detail_order_service.model.res.CourseRes;
import com.java.detail_order_service.model.res.DetailOrderRes;
import com.java.detail_order_service.repository.DetailOrderRepository;
import com.java.detail_order_service.rest.inter.CourseResService;
import com.java.detail_order_service.service.inter.DetailOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DetailOrderServiceImpl implements DetailOrderService {

    private final DetailOrderRepository detailOrderRepository;

    private final CourseResService courseResService;
    @Override
    @Transactional
    public DetailOrder createDetailOrder(DetailOrder order) {
        return detailOrderRepository.save(order);
    }

    @Override
    public List<DetailOrderRes> listDetailOrderByOrderId(int id) {

        List<DetailOrder> detailOrders = detailOrderRepository.findAllByOrderId(id);

        List<DetailOrderRes> detailOrderRes = detailOrders.stream().map(c ->{
            CourseRes courses = courseResService.getDetailCourse(c.getCourseId()).block();


            return DetailOrderRes.detailOrderRes(c,courses);
        }).collect(Collectors.toList());

        return detailOrderRes;
    }
}
