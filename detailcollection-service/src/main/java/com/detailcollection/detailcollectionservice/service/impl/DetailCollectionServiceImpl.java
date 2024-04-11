package com.detailcollection.detailcollectionservice.service.impl;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.repository.DetailCollectionRepository;
import com.detailcollection.detailcollectionservice.service.inter.DetailCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DetailCollectionServiceImpl implements DetailCollectionService {

    private final DetailCollectionRepository detailCollectionRepository;
    @Override
    public DetailCollection createDetailCollection(DetailCollection detailCollection) {
        return detailCollectionRepository.save(detailCollection);
    }

    @Override
    public DetailCollection getDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId) {
        return detailCollectionRepository.findDetailCollectionByCollectionIdAndCourseId(collectionId, courseId);
    }
}
