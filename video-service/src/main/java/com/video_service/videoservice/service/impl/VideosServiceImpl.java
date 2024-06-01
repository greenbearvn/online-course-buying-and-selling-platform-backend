package com.video_service.videoservice.service.impl;

import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.obj.Test;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.VideoRes;
import com.video_service.videoservice.repository.VideosRepository;
import com.video_service.videoservice.rest.impl.HttpServiceImpl;
import com.video_service.videoservice.service.inter.VideosService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;
    private final HttpServiceImpl httpService;
    @Override
    public List<Videos> getVideosPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        List<Videos> items =  videosRepository.findAll(pageable).getContent();
        return items;
    }

    @Override
    public List<Videos> getAllVideos() {
        return videosRepository.findAll();
    }

    @Override
    public List<VideoRes> getAllVideosByLesson(int lessonId) {

        List<Videos> videos = videosRepository.findAllVideosByLession(lessonId);

        List<VideoRes> videoRes = videos.stream().map(v -> {
            List<Test> tests = httpService.getAllTestOfVideo(v.getVideoId()).collectList().block();
            return VideoRes.videoResBuilder(v, tests);
        }).collect(Collectors.toList());

        return videoRes;
    }


    @Override
    public Videos getVideosById(int id) {
        Videos item = videosRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return item;
    }

    @Override
    @Transactional
    public Videos createVideos(VideoReq videoReq) {
        Videos newItem = Videos.builder()
                .videoName(videoReq.getVideoName())
                .videoContent(videoReq.getVideoContent())
                .videoLink(videoReq.getVideoLink())
                .videoDuration(videoReq.getVideoDuration())
                .lessionId(videoReq.getLessionId()).build();
        return videosRepository.save(newItem);
    }

    @Override
    @Transactional
    public Videos updateVideos(int id, VideoReq videoReq) {
        // Retrieve the existing Videos entity from the repository using its ID
        Videos existingVideo = videosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + id));

        // Update the attributes of the existing Video entity with values from videoReq
        existingVideo.setVideoName(videoReq.getVideoName());
        existingVideo.setVideoContent(videoReq.getVideoContent());
        existingVideo.setVideoLink(videoReq.getVideoLink());
        existingVideo.setVideoDuration(videoReq.getVideoDuration());
        existingVideo.setLessionId(videoReq.getLessionId());

        // Save the updated entity back to the repository
        return videosRepository.save(existingVideo);
    }


    @Override
    @Transactional
    public boolean deleteVideos(int id) {
        Optional<Videos> item = videosRepository.findById(id);
        if(item.isPresent()){
            videosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
