package com.course_service.courseservice.models.res;



import com.course_service.courseservice.entity.Courses;
import com.course_service.courseservice.models.obj.DetailCate;
import com.course_service.courseservice.models.obj.Levels;
import com.course_service.courseservice.models.obj.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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



    public  static  CourseRes fromCourseRes(Courses courseRes, List<LessionRes> lessionRes, DetailCate detailCate , Levels levels, Profile profile){
        return  CourseRes.builder()
                .courses(courseRes)
                .levels(levels)
                .detailCate(detailCate)
                .lessionRes(lessionRes)
                .profile(profile)
                .build();
    }
}
