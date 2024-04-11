package com.collection_service.collectionservice.model.events;


import com.collection_service.collectionservice.entity.Collections;
import com.collection_service.collectionservice.model.obj.DetailOrder;
import com.collection_service.collectionservice.model.obj.OrderInfor;
import lombok.*;

import java.util.Map;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private String type;

    private OrderInfor order;

    private Map<String, DetailOrder> detailOrder;
}
