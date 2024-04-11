package com.javabackend.paymentservice.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @JsonProperty("productId")
    private int productId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("paymentMode")
    private String paymentMode;

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("address")
    private String address;
}
