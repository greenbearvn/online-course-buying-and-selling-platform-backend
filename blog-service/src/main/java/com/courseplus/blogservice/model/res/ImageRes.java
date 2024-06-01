package com.courseplus.blogservice.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ImageRes {
    @JsonProperty("data")
    private String data;
}
