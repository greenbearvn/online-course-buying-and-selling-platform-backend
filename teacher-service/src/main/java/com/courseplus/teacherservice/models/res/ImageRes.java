package com.courseplus.teacherservice.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

@Setter
public class ImageRes {

    @JsonProperty("data")
    private String data;
}
