package com.courseplus.cartservice.repository.impl;

import com.courseplus.cartservice.model.CartItem;
import com.courseplus.cartservice.repository.inter.BaseRedisRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BaseRedisRepositoryImpl implements BaseRedisRepository {

    private RedisTemplate<String, CartItem> redisTemplate;
    private HashOperations hashOperations;

    public BaseRedisRepositoryImpl(RedisTemplate<String, CartItem> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void addValueToMap(String key, String field, CartItem item) {
        hashOperations.put(key, field, item);
    }

    @Override
    public void saveListData(String key, Map<String, CartItem> map) {
        hashOperations.putAll(key, map);
    }

    @Override
    public Map getMapData(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public boolean isMapEmpty(String key) {
        long size = hashOperations.size(key);
        return size == 0;
    }

    @Override
    public int removeValueFromMap(String key, String field) {
        hashOperations.delete(key, field);
        return 1;
    }

    @Override
    public int removeAllValuesFromMap(String key) {

        redisTemplate.delete(key);
        return 1;
    }
}
