package com.detailcollection.detailcollectionservice.service.inter;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import com.detailcollection.detailcollectionservice.model.obj.DetailCollectionRes;

import java.util.List;

public interface DetailCollectionService {

    public DetailCollection createDetailCollection(DetailCollection detailCollection);

    public List<DetailCollection> getDetailCollectionByCollectionIdAndCourseId(int collectionId, int courseId);

    public List<DetailCollectionRes> getDetailCollectionByCollectionId(int collectionId);

    public DetailCollection getDetailCollection(int collectionId, int courseId);
}
