package com.javabackend.commentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private int commentId;

    @Column(name = "courseId")
    private int courseId;

    @Column(name = "userId")
    private int userId;


    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "timePublish")
    private Date timePublish;

}
