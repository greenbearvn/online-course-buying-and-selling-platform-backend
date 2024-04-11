package com.course_service.courseservice.service.Impl;


import com.course_service.courseservice.models.req.CartItem;
import com.course_service.courseservice.repository.BaseRedisRepository;
import com.course_service.courseservice.service.Inter.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final BaseRedisRepository baseRedisRepository;

    private final String KEY = "cart";

    @Override
    public void addCart( CartItem cartItem) {

        if(baseRedisRepository.isMapEmpty(KEY)) {
            Map<String,CartItem> map = new HashMap<String,CartItem>();
            map.put( "id"+cartItem.getCourseId(), cartItem);
            baseRedisRepository.saveListData(KEY,map);
        }

        if(!baseRedisRepository.isMapEmpty(KEY)){
            baseRedisRepository.addValueToMap(KEY,"id"+cartItem.getCourseId(),cartItem);
        }
    }

    @Override
    public Map getCartItems() {
        return baseRedisRepository.getMapData(KEY);
    }

    @Override
    public int deleteCart() {
        return baseRedisRepository.removeAllValuesFromMap(KEY);
    }

    @Override
    public int deleteOneItem(String fieldName) {
        return baseRedisRepository.removeValueFromMap(KEY,fieldName);
    }


}
