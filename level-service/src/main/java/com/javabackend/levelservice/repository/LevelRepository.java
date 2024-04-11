package com.javabackend.levelservice.repository;

import com.javabackend.levelservice.entity.Levels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Levels, Integer> {
}
