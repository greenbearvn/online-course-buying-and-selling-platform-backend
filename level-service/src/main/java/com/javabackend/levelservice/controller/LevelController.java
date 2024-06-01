package com.javabackend.levelservice.controller;

import com.javabackend.levelservice.entity.Levels;
import com.javabackend.levelservice.model.req.LevelReq;
import com.javabackend.levelservice.service.inter.LevelsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelsService levelsService;

    @GetMapping("/list")
    public ResponseEntity<List<Levels>> getAllLevels() {
        List<Levels> levels =  levelsService.getAllLevels();
        return ResponseEntity.ok().body(levels);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<Levels> getDetailLevel(@PathVariable("id") int id) {
        Levels level =  levelsService.getLevelsById(id);
        return ResponseEntity.ok().body(level);
    }


    @PostMapping("/create")
    public ResponseEntity<Levels> createLevel(@RequestBody LevelReq level) {

        Levels newLevels = levelsService.createLevel(level);

        return ResponseEntity.ok().body(newLevels);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Levels> editLevel(@PathVariable("id") int id ,@RequestBody LevelReq levels) {

        Levels updateLevels = levelsService.updateLevel(id, levels);

        return  ResponseEntity.ok().body(updateLevels);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteLevel(@PathVariable("id") int id ) {

        boolean status = levelsService.deleteLevel(id);

        return  ResponseEntity.ok().body(status).getBody();
    }

}