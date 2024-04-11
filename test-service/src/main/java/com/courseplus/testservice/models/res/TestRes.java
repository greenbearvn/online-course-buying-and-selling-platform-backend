package com.courseplus.testservice.models.res;

import com.courseplus.testservice.entity.Test;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRes {

    @JsonProperty("testId")
    private int testId;

    @JsonProperty("testName")
    private String testName;

    @JsonProperty("teacherId")
    private int teacherId;

    @JsonProperty("videoId")
    private int videoId;

    List<QuestionRes> questions;

    public static TestRes testResBuider(Test test, List<QuestionRes> questions){
        return TestRes.builder().testId(test.getTestId()).testName(test.getTestName()).teacherId(test.getTeacherId()).videoId(test.getVideoId()).questions(questions).build();
    }
}
