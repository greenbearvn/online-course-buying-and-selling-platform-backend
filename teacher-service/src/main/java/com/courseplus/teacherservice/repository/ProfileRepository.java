package com.courseplus.teacherservice.repository;

import com.courseplus.teacherservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile , Integer> {

    @Query(value = "SELECT * FROM profile p WHERE p.is_teacher > 0", nativeQuery = true)
    public List<Profile> findAllByIsTeacher();

}
