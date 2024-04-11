package com.detailcollection.detailcollectionservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detailcollection")
public class DetailCollection {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailCollectionId;

    @Column(name = "collectionId", nullable=false)
    private int collectionId;

    @Column(name = "courseId", nullable=false)
    private int courseId;

    @Column(name = "createDate", nullable=false)
    private Date createDate;
}
