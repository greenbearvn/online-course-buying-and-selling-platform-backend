package com.course_service.courseservice.service.Inter;

import com.course_service.courseservice.entity.Courses;
import com.course_service.courseservice.models.req.CartItem;
import com.course_service.courseservice.models.req.CoursesReq;
import com.course_service.courseservice.models.res.CourseRes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CoursesService {
    public List<Courses> getCoursesPagination(int page, int size, String sortBy);

    public List<Courses> getAllCourses();

    public CourseRes getCoursesById(int id);

    public Courses createCourses(CoursesReq coursesReq);

    public Courses updateCourses(int id,CoursesReq coursesReq);

    public boolean deleteCourses(int id);
    public String uploadFile(String fileName,MultipartFile file) throws IOException;

    public void displayFile(String fileCode, HttpServletResponse response) throws IOException;

    public void addCart(CartItem cartItem);

    public List<CourseRes> getListProduct();

    public List<CourseRes> getCoursesRecommend(int courseId);

}
