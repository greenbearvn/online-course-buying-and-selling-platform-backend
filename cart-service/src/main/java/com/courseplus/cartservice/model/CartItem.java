package com.courseplus.cartservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @JsonProperty("courseId")
    private int courseId;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("levelId")
    private int levelId;

    @JsonProperty("levelName")
    private String levelName;

    @JsonProperty("profileId")
    private int profileId;

    @JsonProperty("profileName")
    private String  profileName;

    @JsonProperty("courseDuration")
    private String courseDuration;

    @JsonProperty("newPrice")
    private Double newPrice;

    @JsonProperty("oldPrice")
    private Double oldPrice;

    @JsonProperty("courseThumbnail")
    private String courseThumbnail;

}
