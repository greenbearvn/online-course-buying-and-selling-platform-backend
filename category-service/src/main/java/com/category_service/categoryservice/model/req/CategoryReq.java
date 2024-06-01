package com.category_service.categoryservice.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryReq {
    @JsonProperty(value = "categoryId")
    private int categoryId;

    @JsonProperty(value = "categoryName")
    private String categoryName;

    @JsonProperty(value = "image")
    private String image;
}
