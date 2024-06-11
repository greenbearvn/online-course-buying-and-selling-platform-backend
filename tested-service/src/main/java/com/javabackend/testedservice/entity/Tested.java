package com.javabackend.testedservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tested")
public class Tested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testedId")
    private int testedId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "testId")
    private int testId;

    @Column(name = "dateFinish")
    private Date dateFinish;

    @Column(name = "corrected")
    private int corrected;
}
