package com.collection_service.collectionservice.model.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

}
