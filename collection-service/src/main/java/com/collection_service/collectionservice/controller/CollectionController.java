package com.collection_service.collectionservice.controller;


import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.model.res.CollectionRes;
import com.collection_service.collectionservice.service.inter.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping("/user/{userId}")
    public Collections getCollectionByUserId(@PathVariable int userId) {
      Collections collections =  collectionService.getCollectionByUserId(userId);
      return collections;
    }


    @GetMapping("/list")
    public List<CollectionRes> getAll() {

        return collectionService.getAllCollection();
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {

        return collectionService.delete(id);
    }


}
