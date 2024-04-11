package com.detailcate.detailcateservice.service.inter;



import com.detailcate.detailcateservice.entity.DetailCates;
import com.detailcate.detailcateservice.model.req.DetailCateReq;

import java.util.List;

public interface DetailCatesService {
    public List<DetailCates> getCategoryPagination(int page, int size, String sortBy);

    public List<DetailCates> getAllDetailCates();

    public List<DetailCates> getAllDetailCatesByCatId(int cateId);

    public DetailCates getDetailCatesById(int id);

    public DetailCates createDetailCates(DetailCateReq detailCateReq);

    public DetailCates updateDetailCates(int id,DetailCateReq detailCateReq);

    public boolean deleteDetailCates(int id);
}
