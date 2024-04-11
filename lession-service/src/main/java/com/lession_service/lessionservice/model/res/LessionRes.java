package com.lession_service.lessionservice.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lession_service.lessionservice.entity.Lessions;
import com.lession_service.lessionservice.model.obj.Video;
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

    public  static  LessionRes fromLessionRes(Lessions lessions, List<VideoRes> videos){
        return  LessionRes.builder()
                .lessionId(lessions.getLessionId())
                .lessionName(lessions.getLessionName())
                .lessionDuration(lessions.getLessionDuration())
                .courseId(lessions.getCourseId())
                .videos(videos).build();
    }

}
