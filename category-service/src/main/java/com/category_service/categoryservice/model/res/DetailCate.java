package com.category_service.categoryservice.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DetailCate {
    @JsonProperty("detailCateId")
    private int detailCateId;


    @JsonProperty("detailCateName")
    private String detailCateName;

    @JsonProperty("cateId")
    private int cateId;
}
