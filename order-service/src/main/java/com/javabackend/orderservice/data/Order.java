package com.javabackend.orderservice.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "status")
    private int status;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "moneyTotal")
    private double moneyTotal;
}
