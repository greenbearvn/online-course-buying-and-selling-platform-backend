package com.lession_service.lessionservice.repository;

import com.lession_service.lessionservice.entity.Lessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessionsRepository extends JpaRepository<Lessions, Integer> {

    @Query("SELECT l FROM Lessions l WHERE l.courseId = :courseId")
    List<Lessions> findAllLessionByCourseId(int courseId);
}
