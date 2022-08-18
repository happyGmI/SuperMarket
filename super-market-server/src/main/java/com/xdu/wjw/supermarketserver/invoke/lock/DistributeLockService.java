package com.xdu.wjw.supermarketserver.invoke.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author weijunwei
 */
@Service
public class DistributeLockService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public boolean acquireLock(String lockIdStr, Integer lockSeconds) {


        return true;
    }

}
