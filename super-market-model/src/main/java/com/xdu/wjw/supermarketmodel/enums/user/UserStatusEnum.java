package com.xdu.wjw.supermarketmodel.enums.user;

import java.util.HashMap;
import java.util.Map;

public enum UserStatusEnum {

    NEW_CUSTOMER(1, "NEW_CUSTOMER"),
    ONLINE(2, "ONLINE"),
    LOGOUT(3, "LOGOUT");


    private static final Map<Integer, UserStatusEnum> userStatusEnumMap;

    static {
        userStatusEnumMap = new HashMap<>(16);
        for (UserStatusEnum value : UserStatusEnum.values()) {
            userStatusEnumMap.put(value.code, value);
        }
    }

    private int code;
    private String type;
    UserStatusEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getUserTypeByCode(int code) {
        return userStatusEnumMap.get(code).type;
    }
}
