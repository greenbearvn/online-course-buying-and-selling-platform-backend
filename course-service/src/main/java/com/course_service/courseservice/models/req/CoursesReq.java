package com.course_service.courseservice.models.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CoursesReq {

    @JsonProperty("courseId")
    private int courseId;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("courseThumbnail")
    private String courseThumbnail;

    @JsonProperty("shortDes")
    private String shortDes;

    @JsonProperty("fullDes")
    private String fullDes;

    @JsonProperty("timePublished")
    private Date timePublished;

    @JsonProperty("courseDuration")
    private String courseDuration;

    @JsonProperty("oldPrice")
    private Double oldPrice;

    @JsonProperty("percentSale")
    private Double percentSale;

    @JsonProperty("idLevel")
    private int idLevel;

    @JsonProperty("idDetailCate")
    private int idDetailCate;

    @JsonProperty("status")
    private int status;

    @JsonProperty("profileId")
    private int profileId;
}
