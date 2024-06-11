package com.collection_service.collectionservice.model.res;


import com.collection_service.collectionservice.entity.Collections;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionRes {
    private Collections collections;

    private UserRes userRes;

    public static CollectionRes collectionResBuilder(Collections collections,UserRes userRes){
        return  CollectionRes.builder().collections(collections).userRes(userRes).build();
    }
}
