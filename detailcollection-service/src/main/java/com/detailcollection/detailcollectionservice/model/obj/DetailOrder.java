package com.detailcollection.detailcollectionservice.model.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrder {

    private int orderId;
    private int courseId;
    private int teacherId;
    private int levelId;
    private double price;
}
