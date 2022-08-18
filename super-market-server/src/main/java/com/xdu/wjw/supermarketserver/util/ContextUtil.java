package com.xdu.wjw.supermarketserver.util;

import java.util.Map;

/**
 * @Class: ContextUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 12:18
 * @Description:
 */
public class ContextUtil {
    private static ThreadLocal<Object> context = new ThreadLocal<>();
    public static void put(String key, Object value) {
        context.set(value);
    }
    public static Object get() {
        return context.get();
    }
    public static void clean() {
        context.remove();
    }
}
