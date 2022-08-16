package com.xdu.wjw.supermarketserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Class: CacheUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/16 23:30
 * @Description:
 */
public class CacheUtil {

    @Autowired
    @Qualifier(value = "redisTemplate")
    RedisTemplate<String, Object> redisTemplate;

    private final Long CACHE_EXPIRE_TIME = 30 * 60L;

    public static CacheUtil getCacheUtil() {
        return new CacheUtil();
    }

    @Transactional(rollbackFor = {Exception.class})
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }


}
