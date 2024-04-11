package com.courseplus.testservice.models.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestReq {


    @JsonProperty("testId")
    private int testId;

    @JsonProperty("testId")
    private String testName;

    @JsonProperty("testId")
    private int teacherId;

    @JsonProperty("testId")
    private int videoId;
}
