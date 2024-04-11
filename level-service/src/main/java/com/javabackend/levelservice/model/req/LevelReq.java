package com.javabackend.levelservice.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LevelReq {

    @JsonProperty(value = "idLevels")
    private int idLevels;

    @JsonProperty(value = "nameLevel")
    private String nameLevel;

}
