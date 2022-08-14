package com.xdu.wjw.supermarketserver.util;

import java.util.Map;

/**
 * @Class: ContextUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 12:18
 * @Description:
 */
public class ContextUtil {

    private static ThreadLocal<Map<String, Object>> userContext = new ThreadLocal<>();

    public static void put() {

    }
    public static void get() {

    }
    public static void clean() {
        userContext.remove();
    }
}
