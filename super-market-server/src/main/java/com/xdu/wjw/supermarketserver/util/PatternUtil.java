package com.xdu.wjw.supermarketserver.util;

import com.xdu.wjw.supermarketmodel.contants.PatternConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

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

    public static void main(String[] args) {
        System.out.println(PatternUtil.match(PatternConstant.PHONE_CHECK_PATTERN, "13174530720"));
        System.out.println(PatternUtil.match(PatternConstant.EMAIL_CHECK_PATTERN, "13174530720@qq.com"));
        System.out.println(StringUtils.isEmpty("null"));
        System.out.println(NumberUtils.isDigits("123a"));
    }
}
