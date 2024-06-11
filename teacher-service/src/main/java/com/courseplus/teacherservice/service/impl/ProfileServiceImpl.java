package com.courseplus.teacherservice.service.impl;

import com.courseplus.teacherservice.entity.Profile;
import com.courseplus.teacherservice.repository.ProfileRepository;
import com.courseplus.teacherservice.service.inter.ProfileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final String UPLOAD_DIR = "D:\\Data\\FinalProject\\JavaMicroservices\\teacher-service\\uploads";
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8000/api/profile/display/";

    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getDetailProfile(int profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<Profile> getProfileIsTeacher() {
        return profileRepository.findAllByIsTeacher();
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(int id, Profile profileReq) {
        Profile profile = profileRepository.findById(id).orElseThrow();

        profile.setProfileId(id);
        profile.setProfileName(profileReq.getProfileName());
        profile.setEmail(profileReq.getEmail());
        profile.setAvatar(profileReq.getAvatar());
        profile.setDesciption(profileReq.getDesciption());
        profile.setPhoneNumber(profileReq.getPhoneNumber());
        profile.setCateId(profileReq.getCateId());
        profile.setIsTeacher(profileReq.getIsTeacher());

        return profileRepository.save(profile);
    }

    @Override
    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        Path uploadDirect = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadDirect)) {
            Files.createDirectories(uploadDirect);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try(InputStream inputStream = file.getInputStream()){
            Path filePath = uploadDirect.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = BASE_URL_DISPLAY_IMG + filePath.getFileName().toString();
            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayFile(String fileCode, HttpServletResponse response) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, fileCode);

        String contentType = Files.probeContentType(filePath);
        response.setContentType(contentType);

        // Copy the file content directly to the response output stream
        Files.copy(filePath, response.getOutputStream());
    }

    @Override
    public boolean delete(int id) {
        try{

            profileRepository.deleteById(id);
            return true;

        }catch (Exception e){
            return  false;
        }


    }
}
