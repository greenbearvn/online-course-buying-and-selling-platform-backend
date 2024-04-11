package com.courseplus.teacherservice.service.impl;

import com.courseplus.teacherservice.entity.Profile;
import com.courseplus.teacherservice.repository.ProfileRepository;
import com.courseplus.teacherservice.service.inter.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    @Override
    public Profile getDetailProfile(int profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<Profile> getProfileIsTeacher() {
        return profileRepository.findAllByIsTeacher();
    }
}
