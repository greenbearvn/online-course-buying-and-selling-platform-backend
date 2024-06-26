package com.course_service.courseservice.repository;

import com.course_service.courseservice.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses,Integer> {

    @Query(value = "SELECT id_detail_cate FROM courses c WHERE c.course_id = ?1", nativeQuery = true)
    public int findIdDetailLevelByCourseId(int courseId);


    public List<Courses> findAllByIdDetailCate(int detailCateId);

    public List<Courses> findAllByProfileId(int id);


}
