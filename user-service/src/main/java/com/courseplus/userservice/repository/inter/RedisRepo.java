package com.courseplus.userservice.repository.inter;

import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.UserRes;

import java.util.Map;

public interface RedisRepo {

    public void addValueToMap(String key, String field, UserRes userRes);

    public Map getMapData(String key);

    public boolean removeValueFromMap(String key, String field);
}
