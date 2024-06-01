package com.detailcollection.detailcollectionservice.controller;


import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.service.inter.DetailCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
