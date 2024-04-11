package com.course_service.courseservice.models.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCate {

    @JsonProperty("detailCateId")
    private int detailCateId;


    @JsonProperty("detailCateName")
    private String detailCateName;

    @JsonProperty("cateId")
    private int cateId;
}
