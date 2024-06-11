package com.video_service.videoservice.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.video_service.videoservice.entity.Videos;
import com.video_service.videoservice.model.obj.Test;
import com.video_service.videoservice.model.req.VideoReq;
import com.video_service.videoservice.model.res.VideoRes;
import com.video_service.videoservice.repository.VideosRepository;
import com.video_service.videoservice.rest.impl.HttpServiceImpl;
import com.video_service.videoservice.service.inter.VideosService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;
    private final HttpServiceImpl httpService;
    private final Cloudinary cloudinary;

    private final String UPLOAD_DIR = "D:\\Data\\FinalProject\\JavaMicroservices\\video-service\\uploads";
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8086/api/v1/videos/display/";

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
    public String uploadVideo(MultipartFile multipartFile) throws IOException {
        Map<?, ?> uploadResult =  cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
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

    @Override
    public String uploadFile(MultipartFile videoFile) throws IOException {
        try {
            // Ensure the upload directory exists
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Generate a unique file code
            String fileCode = RandomStringUtils.randomAlphanumeric(8);

            // Get the original filename
            String originalFilename = videoFile.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("Video file has no original filename");
            }

            // Resolve the file path and copy the uploaded video to the destination
            Path videoFilePath = uploadDir.resolve(fileCode + "-" + originalFilename);
            Files.copy(videoFile.getInputStream(), videoFilePath, StandardCopyOption.REPLACE_EXISTING);

            // Construct the URL for accessing the uploaded video
            String videoUrl = BASE_URL_DISPLAY_IMG + videoFilePath.getFileName().toString();
            return videoUrl;
        } catch (IOException e) {
            throw new IOException("Failed to upload video", e);
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
}
