package com.courseplus.userservice.models.res;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileUserRes {

    private UserRes userRes;
    private Profile profile;

    public static ProfileUserRes profileUserRes(UserRes userRes,Profile profile){
        return ProfileUserRes.builder().userRes(userRes).profile(profile).build();
    }
}
