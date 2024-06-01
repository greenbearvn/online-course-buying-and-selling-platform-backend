package com.courseplus.questionservice.models.req;

import com.courseplus.questionservice.models.res.QuestionRes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class TestReq {
    @JsonProperty("testId")
    private int testId;

    @JsonProperty("testName")
    private String testName;

    @JsonProperty("teacherId")
    private int teacherId;

    @JsonProperty("videoId")
    private int videoId;

    @JsonProperty("questions")
    List<QuestionRes> questions;
}
