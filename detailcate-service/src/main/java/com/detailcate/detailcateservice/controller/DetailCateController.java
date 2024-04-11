package com.detailcate.detailcateservice.controller;


import com.detailcate.detailcateservice.entity.DetailCates;
import com.detailcate.detailcateservice.model.req.DetailCateReq;
import com.detailcate.detailcateservice.service.inter.DetailCatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detailcates")
@RequiredArgsConstructor
public class DetailCateController {
    
    private final DetailCatesService detailCatesService;

    @GetMapping("/list")
    public ResponseEntity<List<DetailCates>> getAllDetailCates() {
        List<DetailCates> detailCates =  detailCatesService.getAllDetailCates();
        return ResponseEntity.ok().body(detailCates);
    }


    @GetMapping("/list/category/{cateId}")
    public ResponseEntity<List<DetailCates>> getAllDetailCateById(@PathVariable int cateId) {
        List<DetailCates> detailCates = detailCatesService.getAllDetailCatesByCatId(cateId);
        return ResponseEntity.ok(detailCates);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<DetailCates>> getDetailCatesPagination(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortBy") String sortBy) {
        List<DetailCates> DetailCates =  detailCatesService.getCategoryPagination(page, size, sortBy);
        return ResponseEntity.ok().body(DetailCates);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DetailCates> getDetailDetailCates(@PathVariable("id") int id) {
        DetailCates DetailCates =  detailCatesService.getDetailCatesById(id);
        return ResponseEntity.ok().body(DetailCates);
    }


    @PostMapping("/create")
    public ResponseEntity<DetailCates> createDetailCates(@RequestBody DetailCateReq detailCateReq) {

        DetailCates newDetailCates = detailCatesService.createDetailCates(detailCateReq);

        return ResponseEntity.ok().body(newDetailCates);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DetailCates> editDetailCates(@RequestParam("id") int id , @RequestBody DetailCateReq detailCateReq) {

        DetailCates updateDetailCates = detailCatesService.updateDetailCates(id, detailCateReq);

        return  ResponseEntity.ok().body(updateDetailCates);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteDetailCates(@RequestParam("id") int id ) {

        boolean status = detailCatesService.deleteDetailCates(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
}
