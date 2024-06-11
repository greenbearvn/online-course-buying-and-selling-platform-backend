package com.collection_service.collectionservice.service.impl;

import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.model.events.OrderEvent;
import com.collection_service.collectionservice.model.obj.OrderInfor;
import com.collection_service.collectionservice.model.req.CollectionReq;
import com.collection_service.collectionservice.model.res.CollectionRes;
import com.collection_service.collectionservice.model.res.UserRes;
import com.collection_service.collectionservice.repository.CollectionRepository;
import com.collection_service.collectionservice.rest.inter.UserRestService;
import com.collection_service.collectionservice.service.inter.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRestService userRestService;
    @Override
    public Collections detailCollection(int id) {

        Collections collection = collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        return collection;
    }

    @Override
    public Collections getCollectionByUserId(int userid) {

        Collections collection = collectionRepository.findCollectionsByUserId(userid).orNull();
        return collection;
    }

    @Override
    @Transactional
    public Collections createCollection(Collections collections) {
        return collectionRepository.save(collections);
    }

    @Override
    public List<CollectionRes> getAllCollection() {
        List<Collections> collections = collectionRepository.findAll();
        List<CollectionRes> collectionRes = collections.stream().map(c ->{
            UserRes userRes = userRestService.getDetailUserById(c.getUserId()).block();


            return CollectionRes.collectionResBuilder(c,userRes);
        }).collect(Collectors.toList());

        return collectionRes;
    }

    @Override
    public boolean delete(int id) {
        try {
            collectionRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
