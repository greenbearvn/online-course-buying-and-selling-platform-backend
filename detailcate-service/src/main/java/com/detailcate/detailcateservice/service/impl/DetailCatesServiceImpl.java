package com.detailcate.detailcateservice.service.impl;

import com.detailcate.detailcateservice.entity.DetailCates;
import com.detailcate.detailcateservice.model.req.DetailCateReq;
import com.detailcate.detailcateservice.repository.DetailCateRepository;
import com.detailcate.detailcateservice.service.inter.DetailCatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DetailCatesServiceImpl implements DetailCatesService {

    private final DetailCateRepository detailCateRepository;
    @Override
    public List<DetailCates> getCategoryPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);

        List<DetailCates> categories =  detailCateRepository.findAll(pageable).getContent();

        return categories;
    }

    @Override
    public List<DetailCates> getAllDetailCates() {
        return detailCateRepository.findAll();
    }

    @Override
    public List<DetailCates> getAllDetailCatesByCatId(int cateId) {
        return detailCateRepository.findAllByCateId(cateId);
    }

    @Override
    public DetailCates getDetailCatesById(int id) {
        DetailCates detailCates = detailCateRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return detailCates;
    }

    @Override
    public DetailCates createDetailCates(DetailCateReq detailCateReq) {
        DetailCates newCate = DetailCates.builder().detailCateName(detailCateReq.getDetailCateName()).cateId(detailCateReq.getCateId()).build();
        return detailCateRepository.save(newCate);
    }

    @Override
    public DetailCates updateDetailCates(int id, DetailCateReq detailCateReq) {
        DetailCates existItem = detailCateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));

        return existItem.builder().detailCateId(id).detailCateName(detailCateReq.getDetailCateName()).cateId(detailCateReq.getCateId()).build();
    }

    @Override
    public boolean deleteDetailCates(int id) {
        Optional<DetailCates> item = detailCateRepository.findById(id);
        if(item.isPresent()){
            detailCateRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
