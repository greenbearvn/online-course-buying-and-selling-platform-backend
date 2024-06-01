package com.courseplus.userservice.repository.impl;

import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.UserRes;
import com.courseplus.userservice.repository.inter.RedisRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RedisRepoImpl implements RedisRepo {
    private RedisTemplate<String, UserRes> redisTemplate;
    private HashOperations hashOperations;

    public RedisRepoImpl(RedisTemplate<String, UserRes> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void addValueToMap(String key, String field,  UserRes userRes) {
        hashOperations.put(key, field, userRes);
    }

    @Override
    public Map getMapData(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public boolean removeValueFromMap(String key, String field) {
        try {
            hashOperations.delete(key, field);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
