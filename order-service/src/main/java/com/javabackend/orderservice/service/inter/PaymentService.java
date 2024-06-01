package com.javabackend.orderservice.service.inter;

import com.javabackend.orderservice.models.res.PaymentRes;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    public PaymentRes.VNPayResponse createVnPayPayment(HttpServletRequest request);
}
