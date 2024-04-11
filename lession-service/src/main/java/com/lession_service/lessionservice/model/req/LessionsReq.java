package com.lession_service.lessionservice.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LessionsReq {

    @JsonProperty("lessionName")
    private String lessionName;

    @JsonProperty( "lessionDuration")
    private String lessionDuration;

    @JsonProperty( "courseId")
    private int courseId;
}
