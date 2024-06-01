package com.javabackend.orderservice.models.res;

import lombok.Builder;

public abstract class PaymentRes {
    @Builder
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
