package com.detailcollection.detailcollectionservice.controller;


import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.model.obj.DetailCollectionRes;
import com.detailcollection.detailcollectionservice.service.inter.DetailCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailcollection")
@RequiredArgsConstructor
public class DetailCollectionCotroller {

    private final DetailCollectionService detailCollectionService;

    @GetMapping("/detail/{collectionId}/{courseid}")
    public ResponseEntity<List<DetailCollection>> getDetailCourses(@PathVariable("collectionId") int collectionId, @PathVariable("courseid") int courseId) {

        List<DetailCollection> detailCollection = detailCollectionService.getDetailCollectionByCollectionIdAndCourseId(collectionId, courseId);
        return ResponseEntity.ok().body(detailCollection);

    }

    @GetMapping("/detail")
    public ResponseEntity<DetailCollection> getDetailCollection(@RequestParam("collectionId") int collectionId, @RequestParam("courseId") int courseId) {

        DetailCollection detailCollection = detailCollectionService.getDetailCollection(collectionId, courseId);
        return ResponseEntity.ok().body(detailCollection);

    }

    @GetMapping("/colllection/{collectionId}")
    public ResponseEntity<List<DetailCollectionRes>> getAllByCollectionId(@PathVariable("collectionId") int collectionId) {

        List<DetailCollectionRes> detailCollection = detailCollectionService.getDetailCollectionByCollectionId(collectionId);
        return ResponseEntity.ok().body(detailCollection);

    }

}
