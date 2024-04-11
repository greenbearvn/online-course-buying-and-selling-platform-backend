package com.detailcate.detailcateservice.repository;

import com.detailcate.detailcateservice.entity.DetailCates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailCateRepository extends JpaRepository<DetailCates, Integer> {
    List<DetailCates> findAllByCateId(int cateId);
}
