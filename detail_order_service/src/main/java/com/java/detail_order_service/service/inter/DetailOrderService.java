package com.java.detail_order_service.service.inter;


import com.java.detail_order_service.entity.DetailOrder;
import com.java.detail_order_service.model.res.DetailOrderRes;

import java.util.List;

public interface DetailOrderService {

    public DetailOrder createDetailOrder(DetailOrder order);

    public List<DetailOrderRes> listDetailOrderByOrderId(int id);

}