package com.javabackend.testedservice.models.res;

import com.javabackend.testedservice.entity.Tested;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestedRes {

    private Tested tested;
    private Test test;
    private Profile profile;

    public static TestedRes testResBuilder(Tested tested, Test test,Profile profile){
        return TestedRes.builder().tested(tested).test(test).profile(profile).build();
    }
}
