package com.detailcollection.detailcollectionservice.service.inter;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;

public interface DetailCollectionService {

    public DetailCollection createDetailCollection(DetailCollection detailCollection);

    public DetailCollection getDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId);
}
