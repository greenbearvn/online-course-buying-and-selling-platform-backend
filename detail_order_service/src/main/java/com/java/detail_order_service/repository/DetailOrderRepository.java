package com.java.detail_order_service.repository;

import com.java.detail_order_service.entity.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailOrderRepository extends JpaRepository<DetailOrder, Integer> {

    public List<DetailOrder> findAllByOrderId(int id);
}
