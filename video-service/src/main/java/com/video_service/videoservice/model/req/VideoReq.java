package com.video_service.videoservice.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class VideoReq {


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
}
