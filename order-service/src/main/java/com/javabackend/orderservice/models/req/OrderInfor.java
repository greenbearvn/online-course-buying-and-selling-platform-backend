package com.javabackend.orderservice.models.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.javabackend.orderservice.models.obj.DetailOrder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
public class OrderInfor {

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("status")
    private int status;

    @JsonProperty("createAt")
    private Date createAt;

    @JsonProperty("moneyTotal")
    private double moneyTotal;

    @JsonProperty("detailOrder")
    Map<String, DetailOrder> detailOrder;

}
