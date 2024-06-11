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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private final String BASE_URL_DISPLAY_IMG = "http://localhost:8084/api/courses/display/";


    @Override
    public List<CourseRes> getAllCourses() {

        List<Courses> courses = coursesRepository.findAll();

        return convertListCoursetoListCourseRes(courses);
    }


    @Override
    public Mono<CourseRes> getCoursesById(int id) {

        Mono<Courses> courseMono = Mono.justOrEmpty(coursesRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("Course not found")));

        Flux<LessionRes> lessionResFlux = httpBinService.getLessions(id);
        Mono<DetailCate> detailCateMono = courseMono.flatMap(course -> httpBinService.getDetailCate(course.getIdDetailCate()));
        Mono<Levels> levelsMono = courseMono.flatMap(course -> httpBinService.getLevelOfCourse(course.getIdLevel()));
        Mono<Profile> profileMono = courseMono.flatMap(course -> httpBinService.getDetailTeacherOfCourse(course.getProfileId()));

        return Mono.zip(courseMono, lessionResFlux.collectList(), detailCateMono, levelsMono, profileMono)
                .map(tuple -> {
                    Courses course = tuple.getT1();
                    List<LessionRes> lessionResList = tuple.getT2();
                    DetailCate detailCate = tuple.getT3();
                    Levels levels = tuple.getT4();
                    Profile profile = tuple.getT5();

                    CourseRes courseRes = new CourseRes();
                    courseRes.setCourses(course);
                    courseRes.setLessionRes(lessionResList);
                    courseRes.setDetailCate(detailCate);
                    courseRes.setLevels(levels);
                    courseRes.setProfile(profile);
                    return courseRes;
                });
    }


    @Override
    public Courses detail(int id) {
        return coursesRepository.findById(id).get();
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
                .status(coursesReq.getStatus())
                .profileId(coursesReq.getProfileId())
                .build();
        return coursesRepository.save(newItem);
    }

    @Override
    @Transactional
    public Courses updateCourses(int id, CoursesReq coursesReq) {
        double newPrice = coursesReq.getOldPrice() - (coursesReq.getOldPrice() * coursesReq.getPercentSale()) / 100;

        Courses existItem = coursesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existItem.setCourseId(id);
        existItem.setCourseName(coursesReq.getCourseName());
        existItem.setCourseThumbnail(coursesReq.getCourseThumbnail());
        existItem.setShortDes(coursesReq.getShortDes());
        existItem.setFullDes(coursesReq.getFullDes());
        existItem.setTimePublished(coursesReq.getTimePublished());
        existItem.setCourseDuration(coursesReq.getCourseDuration());
        existItem.setOldPrice(coursesReq.getOldPrice());
        existItem.setPercentSale(coursesReq.getPercentSale());
        existItem.setNewPrice(newPrice);
        existItem.setIdLevel(coursesReq.getIdLevel());
        existItem.setStatus(coursesReq.getStatus());
        existItem.setIdDetailCate(coursesReq.getIdDetailCate());

        return coursesRepository.save(existItem);
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

    @Override
    public List<CourseRes> getAllCourseByProfileId(int id) {

        List<Courses> courses = coursesRepository.findAllByProfileId(id);

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
