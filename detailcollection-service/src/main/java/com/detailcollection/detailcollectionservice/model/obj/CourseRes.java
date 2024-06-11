package com.detailcollection.detailcollectionservice.model.obj;


import com.detailcollection.detailcollectionservice.model.obj.DetailCate;
import com.detailcollection.detailcollectionservice.model.obj.Levels;
import com.detailcollection.detailcollectionservice.model.obj.Profile;
import com.detailcollection.detailcollectionservice.model.obj.Courses;
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



    public  static  CourseRes fromCourseRes(Courses courseRes, List<LessionRes> lessionRes,DetailCate detailCate ,Levels levels, Profile profile){
        return  CourseRes.builder()
                .courses(courseRes)
                .levels(levels)
                .detailCate(detailCate)
                .lessionRes(lessionRes)
                .profile(profile)
                .build();
    }
}
