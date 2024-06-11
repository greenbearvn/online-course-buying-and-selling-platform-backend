package com.java.detail_order_service.model.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessionRes {

    @JsonProperty("lessionId")
    private int lessionId;

    @JsonProperty("lessionName")
    private String lessionName;

    @JsonProperty( "lessionDuration")
    private String lessionDuration;

    @JsonProperty( "courseId")
    private int courseId;

    private List<VideoRes> videos;
}
