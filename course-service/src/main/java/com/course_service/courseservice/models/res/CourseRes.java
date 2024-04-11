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

    private int courseId;

    private String courseName;

    private String courseThumbnail;

    private String shortDes;

    private String fullDes;

    private Date timePublished;

    private String courseDuration;

    private Double oldPrice;

    private Double percentSale;

    private Double newPrice;

    private int status;

    private Profile profile;

    private Levels levels;

    private DetailCate detailCate;

    private List<LessionRes> lessionRes;



    public  static  CourseRes fromCourseRes(Courses courseRes, List<LessionRes> lessionRes,DetailCate detailCate ,Levels levels, Profile profile){
        return  CourseRes.builder()
                .courseId(courseRes.getCourseId())
                .courseName(courseRes.getCourseName())
                .courseThumbnail(courseRes.getCourseThumbnail())
                .shortDes(courseRes.getShortDes())
                .fullDes(courseRes.getFullDes())
                .timePublished(courseRes.getTimePublished())
                .courseDuration(courseRes.getCourseDuration())
                .oldPrice(courseRes.getOldPrice())
                .percentSale(courseRes.getPercentSale())
                .newPrice(courseRes.getNewPrice())
                .levels(levels)
                .detailCate(detailCate)
                .lessionRes(lessionRes)
                .profile(profile)
                .build();
    }
}
