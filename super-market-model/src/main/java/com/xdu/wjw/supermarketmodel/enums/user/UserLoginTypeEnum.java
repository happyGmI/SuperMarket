package com.xdu.wjw.supermarketmodel.enums.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: UserLoginTypeEnum
 * @Author: Wei Junwei
 * @Time: 2022/8/17 9:12
 * @Description:
 */
public enum UserLoginTypeEnum {

    PHONE(1, "VerificationCode"),
    EMAIL(2, "email"),
    VERIFICATION_CODE(3, "verification_code"),
    PASSWORD(4, "password");


    private static final Map<Integer, UserLoginTypeEnum> userLoginTypeEnum;

    static {
        userLoginTypeEnum = new HashMap<>(16);
        for (UserLoginTypeEnum value : UserLoginTypeEnum.values()) {
            userLoginTypeEnum.put(value.getCode(), value);
        }
    }

    private int code;
    private String type;
    UserLoginTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getUserUserLoginByCode(int code) {
        return userLoginTypeEnum.get(code).type;
    }
    public static UserLoginTypeEnum getUserLoginByCode(int code) {
        return userLoginTypeEnum.get(code);
    }

    public Integer getCode() {
        return code;
    }
}
