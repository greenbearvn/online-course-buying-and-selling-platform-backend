package com.collection_service.collectionservice.controller;


import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.service.inter.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
