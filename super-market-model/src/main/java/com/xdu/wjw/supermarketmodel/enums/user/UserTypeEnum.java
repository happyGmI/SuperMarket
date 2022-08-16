package com.xdu.wjw.supermarketmodel.enums.user;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {

    MERCHANT(1, "regular_users"),
    CUSTOMER(2, "member_users");

    private static final Map<Integer, UserTypeEnum> userTypeEnumMap;

    static {
        userTypeEnumMap = new HashMap<>(16);
        for (UserTypeEnum value : UserTypeEnum.values()) {
            userTypeEnumMap.put(value.code, value);
        }
    }

    private int code;
    private String type;
    UserTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getUserTypeByCode(int code) {
        return userTypeEnumMap.get(code).type;
    }

    public byte getCode() {
        return (byte) code;
    }
}
