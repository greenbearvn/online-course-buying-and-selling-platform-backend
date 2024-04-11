package com.java.detail_order_service.repository;

import com.java.detail_order_service.entity.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailOrderRepository extends JpaRepository<DetailOrder, Integer> {
}
