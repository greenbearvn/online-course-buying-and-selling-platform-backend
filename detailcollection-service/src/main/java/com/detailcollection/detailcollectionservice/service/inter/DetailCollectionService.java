package com.detailcollection.detailcollectionservice.service.inter;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;

import java.util.List;

public interface DetailCollectionService {

    public DetailCollection createDetailCollection(DetailCollection detailCollection);

    public List<DetailCollection> getDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId);
}
