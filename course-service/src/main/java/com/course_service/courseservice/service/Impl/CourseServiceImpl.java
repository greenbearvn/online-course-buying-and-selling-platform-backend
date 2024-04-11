package com.course_service.courseservice.service.Impl;

import com.course_service.courseservice.entity.Courses;
import com.course_service.courseservice.models.obj.DetailCate;
import com.course_service.courseservice.models.obj.Levels;
import com.course_service.courseservice.models.obj.Profile;
import com.course_service.courseservice.models.req.CartItem;
import com.course_service.courseservice.models.req.CoursesReq;
import com.course_service.courseservice.models.res.CourseRes;
import com.course_service.courseservice.models.res.LessionRes;
import com.course_service.courseservice.repository.CoursesRepository;
import com.course_service.courseservice.rest.inter.HttpService;
import com.course_service.courseservice.service.Inter.CoursesService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    private final HttpService httpBinService;
    private final String UPLOAD_DIR = "D:\\Data\\FinalProject\\JavaMicroservices\\course-service\\uploads";
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8084/api/v1/courses/display/";

    @Override
    public List<Courses> getCoursesPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        List<Courses> courses =  coursesRepository.findAll(pageable).getContent();
        return courses;
    }

    @Override
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }


    @Override
    public CourseRes getCoursesById(int id) {

        List<LessionRes> lessionRes = httpBinService.getLessions(id).collectList().block();
        Courses courses = coursesRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        DetailCate detailCate = httpBinService.getDetailCate(courses.getIdDetailCate()).block();
        Levels levels = httpBinService.getLevelOfCourse(courses.getIdLevel()).block();
        Profile profile = httpBinService.getDetailTeacherOfCourse(courses.getProfileId()).block();

        CourseRes courseRes = CourseRes.fromCourseRes(courses, lessionRes,detailCate,levels,profile);

        return courseRes;
    }


    @Override
    @Transactional
    public Courses createCourses(CoursesReq coursesReq) {

        double newPrice = coursesReq.getOldPrice() - (coursesReq.getOldPrice() * coursesReq.getPercentSale()) / 100;

        Courses newItem = Courses.builder()
                .courseName(coursesReq.getCourseName())
                .courseThumbnail(coursesReq.getCourseThumbnail())
                .shortDes(coursesReq.getShortDes())
                .fullDes(coursesReq.getFullDes())
                .timePublished(coursesReq.getTimePublished())
                .courseDuration(coursesReq.getCourseDuration())
                .oldPrice(coursesReq.getOldPrice())
                .percentSale(coursesReq.getPercentSale())
                .newPrice(newPrice)
                .idLevel(coursesReq.getIdLevel())
                .idDetailCate(coursesReq.getIdDetailCate())
                .build();
        return coursesRepository.save(newItem);
    }

    @Override
    @Transactional
    public Courses updateCourses(int id, CoursesReq coursesReq) {
        double newPrice = coursesReq.getOldPrice() - (coursesReq.getOldPrice() * coursesReq.getPercentSale()) / 100;

        Courses existItem = coursesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        return existItem.builder().courseId(id).courseName(coursesReq.getCourseName())
                .courseThumbnail(coursesReq.getCourseThumbnail())
                .shortDes(coursesReq.getShortDes())
                .fullDes(coursesReq.getFullDes())
                .timePublished(coursesReq.getTimePublished())
                .courseDuration(coursesReq.getCourseDuration())
                .oldPrice(coursesReq.getOldPrice())
                .percentSale(coursesReq.getPercentSale())
                .newPrice(newPrice)
                .idLevel(coursesReq.getIdLevel())
                .idDetailCate(coursesReq.getIdDetailCate())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteCourses(int id) {
        Optional<Courses> item = coursesRepository.findById(id);
        if(item.isPresent()){
            coursesRepository.deleteById(id);
            return true;
        }
        return false;
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

    @Override
    public void addCart(CartItem cartItem) {

    }

    @Override
    public List<CourseRes> getListProduct() {

        List<Courses> courses = coursesRepository.findAll();

        return convertListCoursetoListCourseRes(courses);
    }

    @Override
    public List<CourseRes> getCoursesRecommend(int courseId) {

        int detailCateId = coursesRepository.findIdDetailLevelByCourseId(courseId);

        List<Courses> courses = coursesRepository.findAllByIdDetailCate(detailCateId);

        return convertListCoursetoListCourseRes(courses);
    }

    private List<CourseRes> convertListCoursetoListCourseRes(List<Courses> courses){
        List<CourseRes> courseRes = courses.stream().map(c ->{
            List<LessionRes> lessionRes = httpBinService.getLessions(c.getCourseId()).collectList().block();
            DetailCate detailCate = httpBinService.getDetailCate(c.getIdDetailCate()).block();
            Levels levels = httpBinService.getLevelOfCourse(c.getIdLevel()).block();
            Profile profile = httpBinService.getDetailTeacherOfCourse(c.getProfileId()).block();

            return CourseRes.fromCourseRes(c,lessionRes,detailCate,levels,profile);
        }).collect(Collectors.toList());

        return courseRes;
    }

}
