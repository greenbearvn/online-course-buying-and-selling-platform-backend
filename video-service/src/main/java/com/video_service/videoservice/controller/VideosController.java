package com.video_service.videoservice.controller;


import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.VideoRes;
import com.video_service.videoservice.service.inter.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videos")
public class VideosController {

    private final VideosService videosService;

    @GetMapping("/list")
    public ResponseEntity<List<Videos>> getAllVideos() {
        List<Videos> items =  videosService.getAllVideos();
        return ResponseEntity.ok().body(items);
    }


    @GetMapping("/lession/{lessionId}")
    public List<VideoRes> getVideosByLession(@PathVariable("lessionId") int lessionId) {
        List<VideoRes> items =  videosService.getAllVideosByLesson(lessionId);
        return items;
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Videos>> getVideosPagination(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortBy") String sortBy) {
        List<Videos> items =  videosService.getVideosPagination(page, size, sortBy);
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Videos> getDetailVideos(@PathVariable int id) {
        Videos item =  videosService.getVideosById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/create")
    public ResponseEntity<Videos> createVideos(@RequestBody VideoReq videoReq) {
        Videos newItem = videosService.createVideos(videoReq);
        return ResponseEntity.ok().body(newItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Videos> editVideos(@PathVariable("id") int id ,@RequestBody VideoReq videoReq) {

        Videos updatedItem = videosService.updateVideos(id, videoReq);

        return  ResponseEntity.ok().body(updatedItem);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteVideos(@PathVariable("id") int id ) {

        boolean status = videosService.deleteVideos(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
}
