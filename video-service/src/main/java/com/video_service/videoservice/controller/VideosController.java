package com.video_service.videoservice.controller;


import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.ImageRes;
import com.video_service.videoservice.model.res.VideoRes;
import com.video_service.videoservice.service.inter.VideosService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String imageURL = videosService.uploadVideo(multipartFile);
        return imageURL;
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


    @PostMapping(value = "/upload/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile videoFile) {
        if (videoFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Video file is empty");
        }

        try {
            String videoUrl = videosService.uploadFile(videoFile);
            ImageRes imageRes = new ImageRes();
            imageRes.setData(videoUrl);
            return ResponseEntity.ok(imageRes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video: " + e.getMessage());
        }
    }


    @GetMapping("/display/{fileCode}")
    public void displayImage(@PathVariable String fileCode, HttpServletResponse response) throws IOException {
        videosService.displayFile(fileCode, response);
    }


}
