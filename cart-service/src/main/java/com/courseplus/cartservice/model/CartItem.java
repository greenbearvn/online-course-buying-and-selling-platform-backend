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

    @JsonProperty("idLevel")
    private int levelId;

    @JsonProperty("levelName")
    private int levelName;

    @JsonProperty("courseDuration")
    private String courseDuration;

    @JsonProperty("oldPrice")
    private Double oldPrice;

    @JsonProperty("newPrice")
    private Double newPrice;

    @JsonProperty("courseThumbnail")
    private String courseThumbnail;

}
