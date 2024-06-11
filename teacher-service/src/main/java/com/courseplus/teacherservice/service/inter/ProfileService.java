package com.courseplus.teacherservice.service.inter;

import com.courseplus.teacherservice.entity.Profile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {

    public List<Profile> getAllProfile();
    public Profile getDetailProfile(int profileId);

    public List<Profile> getProfileIsTeacher();

    public Profile createProfile(Profile profile);

    public Profile updateProfile(int id, Profile profile);

    public String uploadFile(String fileName, MultipartFile file) throws IOException;
    public void displayFile(String fileCode , HttpServletResponse response) throws IOException;

    public boolean delete(int id);
}
