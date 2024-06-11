package com.java.detail_order_service.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<Test> tests;
}
