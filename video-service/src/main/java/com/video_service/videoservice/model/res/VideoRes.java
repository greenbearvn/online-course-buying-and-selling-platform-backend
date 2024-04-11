package com.video_service.videoservice.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.obj.Test;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoRes {

    @JsonProperty("videoId")
    private int videoId;

    @JsonProperty("videoName")
    private String videoName;

    @JsonProperty("videoContent")
    private String videoContent;

    @JsonProperty("videoLink")
    private String videoLink;

    @JsonProperty("videoDuration")
    private String videoDuration;

    @JsonProperty("lessionId")
    private int lessionId;

    List<Test> tests;

    public static VideoRes videoResBuilder(Videos video, List<Test> tests){
        return VideoRes.builder()
                .videoId(video.getVideoId())
                .videoName(video.getVideoName())
                .videoContent(video.getVideoContent())
                .videoLink(video.getVideoLink())
                .videoDuration(video.getVideoDuration())
                .lessionId(video.getLessionId())
                .tests(tests)
                .build();
    }
}

