package com.category_service.categoryservice.model.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ImageRes {

    @JsonProperty("data")
    private String data;
}
