package com.video_service.videoservice.entity;


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
@Table(name = "videos")
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoId")
    private int videoId;

    @Column(name = "videoName", length=500)
    private String videoName;

    @Column(name = "videoContent", length=500)
    private String videoContent;

    @Column(name = "videoLink", length=500)
    private String videoLink;

    @Column(name = "videoDuration", length=500)
    private String videoDuration;

    @Column(name = "lessionId", nullable = false)
    private int lessionId;
}
