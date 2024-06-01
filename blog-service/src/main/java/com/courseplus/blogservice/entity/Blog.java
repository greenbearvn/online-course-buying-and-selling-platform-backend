package com.courseplus.blogservice.entity;

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
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogId", length=500, nullable=false)
    private int blogId;

    @Column(name = "blogName", length=500, nullable=false)
    private String blogName;

    @Column(name = "content")
    private String content;

    @Column(name = "thumnail")
    private String thumnail;

    @Column(name = "datePublish")
    private Date datePublish;

    @Column(name = "cateid",  nullable=false)
    private int cateid;

    @Column(name = "userId",  nullable=false)
    private int userId;

    @Column(name = "status",  nullable=false)
    private int status;
}
