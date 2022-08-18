package com.xdu.wjw.supermarketserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Class: CacheUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/16 23:30
 * @Description:
 */
@Component
public class CacheUtilService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private final Long CACHE_EXPIRE_TIME = 30 * 60L;
    @Transactional(rollbackFor = {Exception.class})
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
    }
    public boolean setIfAbsent(String lockId, Object value) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockId, value));
    }
    public boolean compareSetExpire(String key, Object value, Integer expireSeconds) {
        if (!Objects.equals(redisTemplate.opsForValue().get(key), value)) {
            return false;
        }
        return Boolean.TRUE.equals(redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS));
    }
    public boolean compareAndDeleteKey(String key, Object exceptValue) {
        Object value = redisTemplate.opsForValue().get(key);
        if (exceptValue.equals(value)) {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        }
        return false;
    }
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public boolean deleteKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
    public Long getExpertSeconds(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
