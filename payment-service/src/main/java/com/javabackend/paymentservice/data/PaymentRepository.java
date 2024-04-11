package com.javabackend.paymentservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public List<Payment> findByOrderId(int orderId);
}
