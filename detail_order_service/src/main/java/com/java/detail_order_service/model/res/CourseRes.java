package com.java.detail_order_service.model.res;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRes {
    private Courses courses;

    private Profile profile;

    private Levels levels;

    private DetailCate detailCate;

    private List<LessionRes> lessionRes;

}
