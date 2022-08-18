package com.xdu.wjw.supermarketserver.invoke.lock;

import com.xdu.wjw.supermarketserver.util.CacheUtilService;
import com.xdu.wjw.supermarketserver.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author weijunwei
 */
@Service
public class DistributeLockService {
    @Autowired
    CacheUtilService cacheUtilService;

    public boolean acquireLock(String lockIdStr, Integer lockSeconds) {
        String randomInt = "0";
        try {
            randomInt = String.valueOf(ThreadLocalRandom.current().nextInt());
            return cacheUtilService.setIfAbsent(lockIdStr, randomInt) && cacheUtilService.compareSetExpire(lockIdStr, randomInt, lockSeconds);
        } catch (RuntimeException e) {
            int retryTimes = 3;
            for (int i = 0; i < retryTimes; i++) {
                if (cacheUtilService.getValue(lockIdStr) == null) {
                    break;
                }
                releaseLock(lockIdStr, randomInt);
            }
            LogUtil.getRedisLogger(DistributeLockService.class).error("acquireLock exception, storeKey = {}", lockIdStr, e);
        }
        return false;
    }
    public boolean releaseLock(String lockIdStr, Object randomInt) {
        try {
            return cacheUtilService.compareAndDeleteKey(lockIdStr, randomInt);
        } catch (RuntimeException e) {
            LogUtil.getRedisLogger(DistributeLockService.class).error("acquireLock exception, storeKey = {}", lockIdStr, e);
        }
        return false;
    }
    public boolean releaseLock(String lockIdStr) {
        try {
            return cacheUtilService.deleteKey(lockIdStr);
        } catch (RuntimeException e) {
            LogUtil.getRedisLogger(DistributeLockService.class).error("acquireLock exception, storeKey = {}", lockIdStr, e);
        }
        return false;
    }


}
