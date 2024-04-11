package com.collection_service.collectionservice.model.events;


import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.model.obj.DetailOrder;
import lombok.*;

import java.util.Map;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionEvent {

    private String type;

    private Collections collections;
    private Map<String, DetailOrder> detailOrder;
}
