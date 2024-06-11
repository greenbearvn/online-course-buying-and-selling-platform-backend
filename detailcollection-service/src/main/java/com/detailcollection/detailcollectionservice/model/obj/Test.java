package com.detailcollection.detailcollectionservice.model.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
