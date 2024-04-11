package com.detailcollection.detailcollectionservice.model.events;

import com.detailcollection.detailcollectionservice.model.obj.Collections;
import com.detailcollection.detailcollectionservice.model.obj.DetailOrder;
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
