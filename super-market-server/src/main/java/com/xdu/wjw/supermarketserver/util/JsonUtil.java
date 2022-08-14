package com.xdu.wjw.supermarketserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Class: JsonUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 3:00
 * @Description:
 */
public class JsonUtil {

    public static String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }

    public static<T> T jsonStringToObject(String jsonText, Class<T> c) {
        return JSONObject.parseObject(jsonText, c);
    }
}
