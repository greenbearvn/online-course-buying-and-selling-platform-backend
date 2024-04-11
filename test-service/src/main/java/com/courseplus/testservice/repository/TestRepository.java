package com.courseplus.testservice.repository;

import com.courseplus.testservice.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {

    public List<Test> findAllByVideoId(int videoId);
}
