package com.courseplus.testservice.models.res;


import com.courseplus.testservice.entity.Test;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestsRes {

    private Test test;

    private Profile profile;

    public static TestsRes testsRes(Test test,Profile profile){
        return TestsRes.builder().test(test).profile(profile).build();
    }

}
