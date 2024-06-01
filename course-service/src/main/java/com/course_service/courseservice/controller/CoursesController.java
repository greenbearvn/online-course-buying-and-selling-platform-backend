package com.course_service.courseservice.controller;

import com.course_service.courseservice.entity.Courses;
import com.course_service.courseservice.models.req.CartItem;
import com.course_service.courseservice.models.req.CoursesReq;
import com.course_service.courseservice.models.res.CourseRes;
import com.course_service.courseservice.models.res.ImageRes;
import com.course_service.courseservice.service.Inter.CartService;
import com.course_service.courseservice.service.Inter.CoursesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CoursesController {

    private final CoursesService coursesService;

    private final CartService cartService;

    @GetMapping("/list")
    public ResponseEntity<List<Courses>> getAllCategories() {
        List<Courses> items =  coursesService.getAllCourses();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/list/new")
    public ResponseEntity<List<CourseRes>> getListNewProducts() {
        List<CourseRes> items =  coursesService.getListProduct();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/detail/{id}")
    public Object getDetailCourses(@PathVariable int id) {
        try {
            Mono<CourseRes> item = coursesService.getCoursesById(id);
            return ResponseEntity.ok().body(item.block());
        } catch (Exception e) {
            return  coursesService.detail(id);
        }

    }

    @GetMapping("/list/recommend/{id}")
    public ResponseEntity<List<CourseRes>> getCoursesRecommendByCateId(@PathVariable int id) {

         List<CourseRes> items =  coursesService.getCoursesRecommend(id);
        return ResponseEntity.ok().body(items);

    }

    @PostMapping(value = "/uploadImage")
    public ImageRes uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String url = coursesService.uploadFile(fileName,multipartFile);

        ImageRes imageRes = new ImageRes();
        imageRes.setData(url);

        return imageRes;
    }

    @GetMapping("/display/{fileCode}")
    public void displayImage(@PathVariable String fileCode, HttpServletResponse response) throws IOException {
        coursesService.displayFile(fileCode, response);
    }

    @PostMapping("/create")
    public ResponseEntity<Courses> createCourses(@RequestBody CoursesReq coursesReq) {
        Courses newItem = coursesService.createCourses(coursesReq);
        return ResponseEntity.ok().body(newItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Courses> editCourses(@PathVariable("id") int id ,@RequestBody CoursesReq coursesReq) {

        Courses updatedItem = coursesService.updateCourses(id, coursesReq);

        return  ResponseEntity.ok().body(updatedItem);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCourses(@PathVariable("id") int id ) {

        boolean status = coursesService.deleteCourses(id);

        return  ResponseEntity.ok().body(status).getBody();
    }



    /// cart

    @PostMapping("/addCart")
    public void addCart(@RequestBody CartItem cartItem ) {

        cartService.addCart(cartItem);
    }

    @GetMapping("/list/cart")
    public Map getList() {
        return cartService.getCartItems();

    }

    @DeleteMapping("/delete/redis/{field}")
    public int deleteOneItem(@PathVariable String field ) {

        return cartService.deleteOneItem(field);

    }


    @DeleteMapping("/removeAll")
    public int removeAll( ) {

        return cartService.deleteCart();

    }

}
