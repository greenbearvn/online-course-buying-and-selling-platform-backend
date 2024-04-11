package com.collection_service.collectionservice.model.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailOrder {

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("courseId")
    private int courseId;

    @JsonProperty("teacherId")
    private int teacherId;

    @JsonProperty("levelId")
    private int levelId;

    @JsonProperty("price")
    private double price;

}
