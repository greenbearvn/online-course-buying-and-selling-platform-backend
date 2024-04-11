package com.video_service.videoservice.service.inter;

import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.VideoRes;

import java.util.List;

public interface VideosService {

    public List<Videos> getVideosPagination(int page, int size, String sortBy);

    public List<Videos> getAllVideos();


    public List<VideoRes> getAllVideosByLesson(int lessionId);

    public Videos getVideosById(int id);

    public Videos createVideos(VideoReq videoReq);

    public Videos updateVideos(int id,VideoReq videoReq);

    public boolean deleteVideos(int id);
    
}
