package com.courseplus.teacherservice.controller;

import com.courseplus.teacherservice.entity.Profile;
import com.courseplus.teacherservice.models.res.ImageRes;
import com.courseplus.teacherservice.service.inter.ProfileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;


    @GetMapping("/list")
    public ResponseEntity<List<Profile>> getAllProfile() {
        List<Profile> items =  profileService.getAllProfile();
        return ResponseEntity.ok().body(items);
    }
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

    @PostMapping("/create")
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        Profile items =  profileService.createProfile(profile);
        return ResponseEntity.ok().body(items);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Profile> update(@PathVariable int id,@RequestBody Profile profile) {
        Profile items =  profileService.createProfile(profile);
        return ResponseEntity.ok().body(items);
    }


    @PostMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageRes uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String url = profileService.uploadFile(fileName,multipartFile);

        ImageRes imageRes = new ImageRes();
        imageRes.setData(url);

        return imageRes;
    }

    @GetMapping("/display/{fileCode}")
    public void displayImage(@PathVariable String fileCode, HttpServletResponse response) throws IOException {
        profileService.displayFile(fileCode, response);
    }
}
