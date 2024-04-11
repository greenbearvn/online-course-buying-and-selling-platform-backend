package com.lession_service.lessionservice.controller;


import com.lession_service.lessionservice.entity.Lessions;
import com.lession_service.lessionservice.model.req.LessionsReq;
import com.lession_service.lessionservice.model.res.LessionRes;
import com.lession_service.lessionservice.service.inter.LessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessions")
public class LessionsController {

    private final LessionsService lessionsService;

    @GetMapping("/list")
    public ResponseEntity<List<Lessions>> getAllLessions() {
        List<Lessions> items =  lessionsService.getAllLessions();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Lessions>> getLessionsPagination(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortBy") String sortBy) {
        List<Lessions> items =  lessionsService.getLessionsPagination(page, size, sortBy);
        return ResponseEntity.ok().body(items);
    }

    // detail page
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessionRes>> getLessionOfCourse(@PathVariable("courseId") int courseId) {
        List<LessionRes> items =  lessionsService.findAllLessionByCourseId(courseId);
        return ResponseEntity.ok().body(items);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<Lessions> getDetailLessions(@PathVariable int id) {
        Lessions item =  lessionsService.getLessionsById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/create")
    public ResponseEntity<Lessions> createLessions(@RequestBody LessionsReq lessionsReq) {
        Lessions newItem = lessionsService.createLessions(lessionsReq);
        return ResponseEntity.ok().body(newItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Lessions> editLessions(@RequestParam("id") int id ,@RequestBody LessionsReq lessionsReq) {

        Lessions updatedItem = lessionsService.updateLessions(id, lessionsReq);

        return  ResponseEntity.ok().body(updatedItem);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteLessions(@RequestParam("id") int id ) {

        boolean status = lessionsService.deleteLessions(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
}
