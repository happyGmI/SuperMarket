package com.xdu.wjw.supermarketserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class: LogUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/16 23:06
 * @Description:
 */
public class LogUtil {

    private static final String SCHEDULE_TASK = "schedule_task";
    private static final String REDIS = "redis";

    public static Logger getScheduleTaskLogger() {
        return LoggerFactory.getLogger(SCHEDULE_TASK);
    }

    public static Logger getCommonLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static Logger getRedisLogger(Class<?> redisClassType) {
        return LoggerFactory.getLogger(redisClassType);
    }

}
