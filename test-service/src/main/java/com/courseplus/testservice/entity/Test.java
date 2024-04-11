package com.courseplus.testservice.entity;


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
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testId")
    private int testId;

    @Column(name = "testName", length=500)
    private String testName;

    @Column(name = "teacherId", nullable = false)
    private int teacherId;

    @Column(name = "videoId", nullable = false)
    private int videoId;
}
