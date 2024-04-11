package com.collection_service.collectionservice.service.inter;

import com.collection_service.collectionservice.entity.Collections;

import com.collection_service.collectionservice.model.req.CollectionReq;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CollectionService {

    public Collections detailCollection(int id);


    public Collections getCollectionByUserId(int userid);

    public Collections createCollection(Collections collections);


}
