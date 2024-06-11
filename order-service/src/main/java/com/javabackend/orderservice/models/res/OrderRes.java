package com.javabackend.orderservice.models.res;

import com.javabackend.orderservice.data.Order;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRes {

    private Order order;

    private UserRes userRes;

    public static OrderRes orderResBuilder(Order order,UserRes userRes){
        return OrderRes.builder().order(order).userRes(userRes).build();
    }
}
