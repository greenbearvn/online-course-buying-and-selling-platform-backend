package com.java.detail_order_service.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Courses {

    @JsonProperty( "courseId")
    private int courseId;

    @JsonProperty( "courseName" )
    private String courseName;

    @JsonProperty( "courseThumbnail")
    private String courseThumbnail;

    @JsonProperty("shortDes" )
    private String shortDes;

    @JsonProperty("fullDes" )
    private String fullDes;

    @JsonProperty("timePublished" )
    private Date timePublished;

    @JsonProperty( "courseDuration")
    private String courseDuration;

    @JsonProperty( "oldPrice")
    private Double oldPrice;

    @JsonProperty("percentSale")
    private Double percentSale;

    @JsonProperty("newPrice")
    private Double newPrice;

    @JsonProperty( "status")
    private int status;

    @JsonProperty( "profileId")
    private int profileId;

    @JsonProperty("idLevel")
    private int idLevel;

    @JsonProperty("idDetailCate")
    private int idDetailCate;
}
