package com.detailcollection.detailcollectionservice.service.impl;

import com.detailcollection.detailcollectionservice.Rest.inter.CourseRestService;
import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.model.obj.CourseRes;
import com.detailcollection.detailcollectionservice.model.obj.Courses;
import com.detailcollection.detailcollectionservice.model.obj.DetailCollectionRes;
import com.detailcollection.detailcollectionservice.repository.DetailCollectionRepository;
import com.detailcollection.detailcollectionservice.service.inter.DetailCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DetailCollectionServiceImpl implements DetailCollectionService {

    private final DetailCollectionRepository detailCollectionRepository;
    private final CourseRestService courseRestService;
    @Override
    public DetailCollection createDetailCollection(DetailCollection detailCollection) {
        return detailCollectionRepository.save(detailCollection);
    }

    @Override
    public List<DetailCollection> getDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId) {
        return detailCollectionRepository.findDetailCollectionByCollectionIdAndCourseId(collectionId, courseId);
    }

    @Override
    public List<DetailCollectionRes> getDetailCollectionByCollectionId(int collectionId) {

        List<DetailCollection> detailCollections = detailCollectionRepository.findDetailCollectionByCollectionId(collectionId);

        List<DetailCollectionRes> detailCollectionRes = detailCollections.stream().map(c ->{
            CourseRes courses = courseRestService.getDetailCourse(c.getCourseId()).block();


            return DetailCollectionRes.detailCollectionBuilder(c,courses);
        }).collect(Collectors.toList());

        return detailCollectionRes;
    }

    @Override
    public DetailCollection getDetailCollection(int collectionId, int courseId) {
        return detailCollectionRepository.findAllByCollectionIdAndCourseId(collectionId,courseId);
    }
}
