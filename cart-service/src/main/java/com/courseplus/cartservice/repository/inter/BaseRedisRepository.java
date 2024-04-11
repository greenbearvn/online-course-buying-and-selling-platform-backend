package com.courseplus.cartservice.repository.inter;

import com.courseplus.cartservice.model.CartItem;

import java.util.Map;

public interface BaseRedisRepository {
    public void saveListData(String key, Map<String, CartItem> map);

    public void addValueToMap(String key,  String field, CartItem item);

    public Map getMapData(String key);

    public boolean isMapEmpty(String key);

    public  int removeValueFromMap(String key, String field);

    public int removeAllValuesFromMap(String key);
}
