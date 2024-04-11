package com.lession_service.lessionservice.model.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Video {

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
}
