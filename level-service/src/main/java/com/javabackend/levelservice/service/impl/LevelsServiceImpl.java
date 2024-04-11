package com.javabackend.levelservice.service.impl;


import com.javabackend.levelservice.entity.Levels;
import com.javabackend.levelservice.model.req.LevelReq;
import com.javabackend.levelservice.repository.LevelRepository;
import com.javabackend.levelservice.service.inter.LevelsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LevelsServiceImpl implements LevelsService {

    private final LevelRepository levelsRepository;
    @Override
    public List<Levels> getLevelPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);

        List<Levels> levels =  levelsRepository.findAll(pageable).getContent();

        return levels;
    }

    @Override
    public List<Levels> getAllLevels() {
        return levelsRepository.findAll();
    }

    @Override
    public Levels getLevelsById(int id) {
        Levels level = levelsRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));;
        return level;
    }

    @Override
    @Transactional
    public Levels createLevel(LevelReq level) {
        Levels newLevels = Levels.builder().nameLevel(level.getNameLevel()).build();
        return levelsRepository.save(newLevels);
    }

    @Override
    @Transactional
    public Levels updateLevel(int id, LevelReq level) {
        Levels existLevel = levelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));

        return existLevel.builder().idLevels(id).nameLevel(level.getNameLevel()).build();
    }

    @Override
    @Transactional
    public boolean deleteLevel(int id) {
        Optional<Levels> level = levelsRepository.findById(id);

        if(level.isPresent()){
            levelsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
