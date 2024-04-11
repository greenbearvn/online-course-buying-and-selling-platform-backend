package com.category_service.categoryservice.model.res;


import com.category_service.categoryservice.entity.Categories;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryRes {

    Categories categories;

    List<DetailCate> detailCateList;
}
