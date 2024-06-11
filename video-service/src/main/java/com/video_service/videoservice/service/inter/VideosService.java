package com.video_service.videoservice.service.inter;

import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.VideoRes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideosService {

    public List<Videos> getVideosPagination(int page, int size, String sortBy);

    public List<Videos> getAllVideos();

    public String uploadVideo(MultipartFile multipartFile) throws IOException;

    public List<VideoRes> getAllVideosByLesson(int lessionId);

    public Videos getVideosById(int id);

    public Videos createVideos(VideoReq videoReq);

    public Videos updateVideos(int id,VideoReq videoReq);

    public boolean deleteVideos(int id);


    public String uploadFile(MultipartFile file) throws IOException;

    public void displayFile(String fileCode, HttpServletResponse response) throws IOException;
    
}
