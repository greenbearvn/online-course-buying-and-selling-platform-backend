package com.courseplus.teacherservice.entity;


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
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;

    @Column(name = "profileName" , length=500, nullable=false)
    private String profileName;

    @Column(name = "email" , length=500, nullable=false)
    private String email;

    @Column(name = "phoneNumber" , length=500, nullable=false)
    private String phoneNumber;

    @Column(name = "avatar" , length=500, nullable=false)
    private String avatar;

    @Column(name = "desciption" , length=500, nullable=false)
    private String desciption;

    @Column(name = "cateId" ,  nullable=false)
    private int cateId;

    @Column(name = "isTeacher", nullable=false)
    private int isTeacher;
}
