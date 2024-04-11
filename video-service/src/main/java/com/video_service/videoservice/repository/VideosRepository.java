package com.video_service.videoservice.repository;

import com.video_service.videoservice.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos,Integer> {

    @Query("SELECT v FROM Videos v WHERE v.lessionId = :lessionId")
    List<Videos> findAllVideosByLession(int lessionId);
}
