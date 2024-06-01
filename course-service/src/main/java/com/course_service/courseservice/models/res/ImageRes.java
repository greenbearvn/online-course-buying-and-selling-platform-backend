package com.course_service.courseservice.models.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRes {

    @JsonProperty("data")
    private String data;
}
