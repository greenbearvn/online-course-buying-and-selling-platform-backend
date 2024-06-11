package com.javabackend.testedservice.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {

    @JsonProperty("testId")
    private int testId;

    @JsonProperty("testName")
    private String testName;

    @JsonProperty("teacherId")
    private int teacherId;

    @JsonProperty("videoId")
    private int videoId;
}
