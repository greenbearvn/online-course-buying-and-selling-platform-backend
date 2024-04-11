package com.javabackend.orderservice.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DetailCollectionRes {

    @JsonProperty("detailCollectionId")
    private int detailCollectionId;

    @JsonProperty("collectionId")
    private int collectionId;

    @JsonProperty("courseId")
    private int courseId;

    @JsonProperty("createDate")
    private Date createDate;
}
