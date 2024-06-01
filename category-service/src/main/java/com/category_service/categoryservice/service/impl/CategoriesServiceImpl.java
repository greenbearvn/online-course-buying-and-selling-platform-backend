package com.category_service.categoryservice.service.impl;

import com.category_service.categoryservice.client.DetailCateClient;
import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;
import com.category_service.categoryservice.model.res.DetailCate;
import com.category_service.categoryservice.repository.CategoriesRepository;
import com.category_service.categoryservice.service.inter.CategoriesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final WebClient webClient;

    private final String UPLOAD_DIR = "D:\\Data\\FinalProject\\JavaMicroservices\\category-service\\uploads";
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8000/api/categories/display/";

    @Override
    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        Path uploadDirect = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadDirect)) {
            Files.createDirectories(uploadDirect);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try(InputStream inputStream = file.getInputStream()){
            Path filePath = uploadDirect.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = BASE_URL_DISPLAY_IMG + filePath.getFileName().toString();
            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayFile(String fileCode, HttpServletResponse response) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, fileCode);

        String contentType = Files.probeContentType(filePath);
        response.setContentType(contentType);

        // Copy the file content directly to the response output stream
        Files.copy(filePath, response.getOutputStream());
    }

    @Override
    public List<Categories> getCategoryPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);

        List<Categories> categories =  categoriesRepository.findAll(pageable).getContent();

        return categories;
    }

    @Override
    public List<Categories> getAllCategories() {
        return  categoriesRepository.findAll();
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
        Categories newCate = Categories.builder()
                .categoryName(categories.getCategoryName())
                .image(categories.getImage()).build();
        return categoriesRepository.save(newCate);
    }

    @Override
    public Categories updateCategory(int id, CategoryReq categories) {
        Categories existLevel = categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));

        return existLevel.builder().categoryId(id)
                .categoryName(categories.getCategoryName())
                .image(categories.getImage()).build();

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
