package com.xdu.wjw.supermarketmodel.enums.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: UserVerificationTypeEnum
 * @Author: Wei Junwei
 * @Time: 2022/8/18 0:26
 * @Description:
 */
public enum UserVerificationTypeEnum {
    VERIFICATION_CODE(3, "verification_code"),
    PASSWORD(4, "password");
    private static final Map<Integer, UserVerificationTypeEnum> userVerificationTypeEnum;

    static {
        userVerificationTypeEnum = new HashMap<>(16);
        for (UserVerificationTypeEnum value : UserVerificationTypeEnum.values()) {
            userVerificationTypeEnum.put(value.getCode(), value);
        }
    }

    private int code;
    private String type;
    UserVerificationTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getUserUserLoginByCode(int code) {
        return userVerificationTypeEnum.get(code).type;
    }
    public static UserVerificationTypeEnum getUserLoginByCode(int code) {
        return userVerificationTypeEnum.get(code);
    }

    public Integer getCode() {
        return code;
    }
}
