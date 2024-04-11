package com.java.detail_order_service.model.obj;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.detail_order_service.entity.DetailOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    Map<String, DetailOrder> detailOrder;

}