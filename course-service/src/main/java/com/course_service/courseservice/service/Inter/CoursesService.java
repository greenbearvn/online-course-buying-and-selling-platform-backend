package com.course_service.courseservice.service.Inter;

import com.course_service.courseservice.entity.Courses;
import com.course_service.courseservice.models.req.CartItem;
import com.course_service.courseservice.models.req.CoursesReq;
import com.course_service.courseservice.models.res.CourseRes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface CoursesService {
    public List<CourseRes> getAllCourses();


//    public CourseRes getCoursesById(int id);

    public Mono<CourseRes> getCoursesById(int id);

    public Courses detail(int id);

    public Courses createCourses(CoursesReq coursesReq);

    public Courses updateCourses(int id,CoursesReq coursesReq);

    public boolean deleteCourses(int id);
    public String uploadFile(String fileName,MultipartFile file) throws IOException;

    public void displayFile(String fileCode, HttpServletResponse response) throws IOException;

    public void addCart(CartItem cartItem);

    public List<CourseRes> getListProduct();

    public List<CourseRes> getCoursesRecommend(int courseId);


    public List<CourseRes> getAllCourseByProfileId(int id);


}
