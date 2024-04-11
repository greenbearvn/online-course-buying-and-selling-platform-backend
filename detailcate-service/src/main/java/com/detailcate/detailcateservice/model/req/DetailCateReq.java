package com.detailcate.detailcateservice.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DetailCateReq {

    @JsonProperty("detailCateId")
    private int detailCateId;


    @JsonProperty("detailCateName")
    private String detailCateName;

    @JsonProperty("cateId")
    private int cateId;
}
