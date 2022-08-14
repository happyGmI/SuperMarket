package com.xdu.wjw.supermarketserver.util;

import java.util.UUID;

/**
 * @Class: UuidUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 17:33
 * @Description:
 */
public class UuidUtil {
    public static String getUuid() {
        return String.valueOf(UUID.randomUUID());
    }
}
