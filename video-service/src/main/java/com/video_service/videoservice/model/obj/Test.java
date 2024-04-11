package com.video_service.videoservice.model.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
