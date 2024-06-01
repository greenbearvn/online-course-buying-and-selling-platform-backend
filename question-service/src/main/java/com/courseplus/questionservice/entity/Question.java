package com.courseplus.questionservice.entity;

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
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private int questionId;

    @Column(name = "testId", nullable = false)
    private int testId;
    @Column(name = "questionDescription", length=500)
    private String questionDescription;

    @Column(name = "suggestion", length=500)
    private String suggestion;
}
