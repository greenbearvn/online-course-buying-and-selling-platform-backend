package com.courseplus.choiceservice.entity;


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
@Table(name = "choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choiceId")
    private int choiceId;

    @Column(name = "questionId", nullable = false)
    private int questionId;

    @Column(name = "choiceContent", length=500)
    private String choiceContent;

    @Column(name = "corrected", nullable = false)
    private int corrected;

}
