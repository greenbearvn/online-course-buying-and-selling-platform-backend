package com.java.detail_order_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detailorder")
public class DetailOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailOrderId")
    private int detailOrderId;

    @Column(name = "orderId", nullable=false)
    private int orderId;

    @Column(name = "courseId", nullable=false)
    private int courseId;

    @Column(name = "teacherId", nullable=false)
    private int teacherId;

    @Column(name = "levelId", nullable=false)
    private int levelId;

    @Column(name = "price", nullable=false)
    private double price;
}
