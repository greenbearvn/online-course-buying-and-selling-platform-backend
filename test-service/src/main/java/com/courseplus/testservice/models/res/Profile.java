package com.courseplus.testservice.models.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @JsonProperty("profileId")
    private int profileId;

    @JsonProperty("profileName")
    private String profileName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("desciption")
    private String desciption;

    @JsonProperty("cateId")
    private int cateId;

    @JsonProperty("isTeacher")
    private int isTeacher;
}
