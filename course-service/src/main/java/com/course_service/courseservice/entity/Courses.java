package com.course_service.courseservice.entity;


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
@Table(name = "Courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    @Column(name = "courseName" , length=500, nullable=false)
    private String courseName;

    @Column(name = "courseThumbnail" , length=500, nullable=true)
    private String courseThumbnail;

    @Column(name = "shortDes" , length=500, nullable=true)
    private String shortDes;

    @Column(name = "fullDes" , length=500, nullable=false)
    private String fullDes;

    @Column(name = "timePublished" , nullable=false)
    private Date timePublished;

    @Column(name = "courseDuration", length=500, nullable=false)
    private String courseDuration;

    @Column(name = "oldPrice", nullable=false)
    private Double oldPrice;

    @Column(name = "percentSale", nullable=false)
    private Double percentSale;

    @Column(name = "newPrice", nullable=false)
    private Double newPrice;

    @Column(name = "status", nullable=false)
    private int status;

    @Column(name = "profileId", nullable=false)
    private int profileId;

    @Column(name = "idLevel", nullable=false)
    private int idLevel;

    @Column(name = "idDetailCate", nullable=false)
    private int idDetailCate;

}
