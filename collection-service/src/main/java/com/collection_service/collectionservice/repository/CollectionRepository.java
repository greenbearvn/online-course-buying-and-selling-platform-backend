package com.collection_service.collectionservice.repository;

import com.collection_service.collectionservice.entity.Collections;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collections, Integer> {

    public Optional<Collections> findCollectionsByUserId(int userid);
}
