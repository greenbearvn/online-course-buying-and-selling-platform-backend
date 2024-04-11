package com.java.detail_order_service.service.impl;

import com.java.detail_order_service.entity.DetailOrder;
import com.java.detail_order_service.repository.DetailOrderRepository;
import com.java.detail_order_service.service.inter.DetailOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DetailOrderServiceImpl implements DetailOrderService {

    private final DetailOrderRepository detailOrderRepository;
    @Override
    @Transactional
    public DetailOrder createDetailOrder(DetailOrder order) {
        return detailOrderRepository.save(order);
    }
}
