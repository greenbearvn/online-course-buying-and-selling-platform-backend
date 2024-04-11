package com.category_service.categoryservice.client;


import com.category_service.categoryservice.model.res.DetailCate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface DetailCateClient {

    @GetExchange ("/list/category/{cateId}")
    public ResponseEntity<List<DetailCate>> getAllDetailCateById(@PathVariable int cateId);
}
