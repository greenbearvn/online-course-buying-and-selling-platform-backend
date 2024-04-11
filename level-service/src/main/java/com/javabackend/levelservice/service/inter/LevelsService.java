package com.javabackend.levelservice.service.inter;

import com.javabackend.levelservice.entity.Levels;
import com.javabackend.levelservice.model.req.LevelReq;

import java.util.List;

public interface LevelsService {

    public List<Levels> getLevelPagination(int page, int size, String sortBy);

    public List<Levels> getAllLevels();

    public Levels getLevelsById(int id);

    public Levels createLevel(LevelReq level);

    public Levels updateLevel(int id,LevelReq level);

    public boolean deleteLevel(int id);
}
