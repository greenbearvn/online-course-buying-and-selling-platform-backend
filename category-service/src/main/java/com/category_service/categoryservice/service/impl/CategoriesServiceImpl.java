package com.category_service.categoryservice.service.impl;

import com.category_service.categoryservice.client.DetailCateClient;
import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;
import com.category_service.categoryservice.model.res.DetailCate;
import com.category_service.categoryservice.repository.CategoriesRepository;
import com.category_service.categoryservice.service.inter.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final WebClient webClient;

    @Override
    public List<Categories> getCategoryPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);

        List<Categories> categories =  categoriesRepository.findAll(pageable).getContent();

        return categories;
    }

    @Override
    public List<Categories> getAllCategories() {
        return null;
    }

    @Override
    public CategoryRes getCategoriesById(int id) {
        Categories categories = categoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        List<DetailCate> detailCates = webClient.get()
                .uri("/list/category/" + id)
                .retrieve()
                .bodyToFlux(DetailCate.class)
                .collectList()
                .block();

        CategoryRes categoryRes = CategoryRes.builder().categories(categories).detailCateList(detailCates).build();

        return categoryRes;
    }

    @Override
    public Categories createCategory(CategoryReq categories) {
        Categories newCate = Categories.builder().categoryName(categories.getCategoryName()).build();
        return categoriesRepository.save(newCate);
    }

    @Override
    public Categories updateCategory(int id, CategoryReq categories) {
        Categories existLevel = categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));

        return existLevel.builder().categoryId(id).categoryName(categories.getCategoryName()).build();
    }

    @Override
    public boolean deleteCategory(int id) {
        Optional<Categories> level = categoriesRepository.findById(id);
        if(level.isPresent()){
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
