package com.detailcate.detailcateservice.entity;

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
@Table(name = "detailcates")
public class DetailCates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_cate_id")
    private int detailCateId;

    @Column(name = "detailCateName" , length=500, nullable=false, unique=false)
    private String detailCateName;

    @Column(name = "cateId", nullable = false)
    private int cateId;

}