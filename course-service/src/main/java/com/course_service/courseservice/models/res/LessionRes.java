package com.course_service.courseservice.models.res;

import com.course_service.courseservice.models.obj.Lessions;
import com.course_service.courseservice.models.obj.VideoRes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessionRes {

    @JsonProperty("lessionId")
    private int lessionId;

    @JsonProperty("lessionName")
    private String lessionName;

    @JsonProperty( "lessionDuration")
    private String lessionDuration;

    @JsonProperty( "courseId")
    private int courseId;

    private List<VideoRes> videos;

    public  static  LessionRes fromLessionRes(LessionRes lessions){
        return  LessionRes.builder()
                .lessionId(lessions.getLessionId())
                .lessionName(lessions.getLessionName())
                .lessionDuration(lessions.getLessionDuration())
                .courseId(lessions.getCourseId())
                .videos(lessions.getVideos())
                .build();
    }
}
