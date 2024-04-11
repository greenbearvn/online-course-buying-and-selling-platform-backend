package com.courseplus.teacherservice.service.inter;

import com.courseplus.teacherservice.entity.Profile;

import java.util.List;

public interface ProfileService {
    public Profile getDetailProfile(int profileId);

    public List<Profile> getProfileIsTeacher();
}
