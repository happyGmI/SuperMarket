package com.xdu.wjw.supermarketserver.util;

import java.util.regex.Pattern;

/**
 * @Class: PatternUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 11:33
 * @Description:
 */
public class PatternUtil {
    public static Boolean match(String pattern, String context) {
        return Pattern.matches(pattern, context);
    }
}
