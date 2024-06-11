package com.detailcollection.detailcollectionservice.model.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRes {

    @JsonProperty("data")
    private String data;
}
