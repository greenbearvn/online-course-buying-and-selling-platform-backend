package com.courseplus.blogservice.service.impl;

import com.courseplus.blogservice.entity.Blog;
import com.courseplus.blogservice.repository.BlogRepository;
import com.courseplus.blogservice.service.inter.BlogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class BlogServiceImpl implements BlogService {

    private final String UPLOAD_DIR = "D:\\Data\\FinalProject\\JavaMicroservices\\blog-service\\uploads";
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8096/api/v1/blog/display/";

    private final BlogRepository blogRepository;
    @Override
    public List<Blog> getBlogsIsPublished() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getNewBlogs() {
        return blogRepository.getNewBlog();
    }

    @Override
    public List<Blog> getRecommendBlogs(int categoryId) {
        return blogRepository.findAllByCateid(categoryId);
    }


    @Override
    public Blog getDetail(int id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Blog create(Blog blog) {


        return blogRepository.save(blog);
    }

    @Override
    public Blog update(int id, Blog blog) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            Blog blogExist = optionalBlog.get();

            blogExist.setBlogId(blog.getBlogId());
            blogExist.setBlogName(blog.getBlogName());
            blogExist.setContent(blog.getContent());
            blogExist.setThumnail(blog.getThumnail()); // Corrected spelling of "Thumbnail"
            blogExist.setDatePublish(blog.getDatePublish());
            blogExist.setCateid(blog.getCateid());
            blogExist.setUserId(blogExist.getUserId()); // This line seems unnecessary, as it sets the same value
            blogExist.setStatus(blog.getStatus());

            return blogRepository.save(blogExist); // Save the updated blog
        } else {
            throw new IllegalArgumentException("Blog with ID " + id + " does not exist."); // Throw an exception if the blog is not found
        }
    }
    @Override
    public boolean delete(int id) {
        try{
            blogRepository.deleteById(id);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

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
    public void displayFile(String fileCode ,HttpServletResponse response) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, fileCode);

        String contentType = Files.probeContentType(filePath);
        response.setContentType(contentType);

        // Copy the file content directly to the response output stream
        Files.copy(filePath, response.getOutputStream());
    }


}
