package com.courseplus.teacherservice.controller;

import com.courseplus.teacherservice.entity.Profile;
import com.courseplus.teacherservice.service.inter.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;


    @GetMapping("/detail/{profileId}")
    public ResponseEntity<Profile> getDetailProfile(@PathVariable("profileId") int profileId) {
        Profile items =  profileService.getDetailProfile(profileId);
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Profile>> getProfileIsTeacher() {
        List<Profile> items =  profileService.getProfileIsTeacher();
        return ResponseEntity.ok().body(items);
    }
}
