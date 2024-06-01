package com.detailcollection.detailcollectionservice.repository;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailCollectionRepository extends JpaRepository<DetailCollection , Integer> {
    @Query(value = "SELECT * FROM detailcollection dc WHERE dc.collection_id = ?1 AND dc.course_id = ?2", nativeQuery = true)
    public List<DetailCollection> findDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId);
}
