package com.xdu.wjw.supermarketmodel.enums.api;

import com.xdu.wjw.supermarketmodel.enums.user.UserStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: ApiResponseEnum
 * @Author: Wei Junwei
 * @Time: 2022/8/14 22:59
 * @Description:
 */
public enum ApiResponseEnum {

    SUCCESS(0,0, 0, "success"),
    UNKNOWN_ERROR(1, 1, 50000, "unknown error"),
    INVALID_ERROR(2, 1, 50001, "invalid error");

    private static final Map<Integer, ApiResponseEnum> ApiResponseEnumMap;

    static {
        ApiResponseEnumMap = new HashMap<>(16);
        for (ApiResponseEnum value : ApiResponseEnum.values()) {
            ApiResponseEnumMap.put(value.code, value);
        }
    }
    private Integer code;
    private Integer status;
    private Integer errorCode;
    private String errorMessage;

    ApiResponseEnum(Integer code, Integer status, Integer errorCode, String errorMessage) {
        this.code = code;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static Integer getStatusByCode(Integer code) {
        return ApiResponseEnumMap.get(code).status;
    }
    public static Integer getErrorCodeByCode(Integer code) {
        return ApiResponseEnumMap.get(code).errorCode;
    }
    public static String getErrorMessageByCode(Integer code) {
        return ApiResponseEnumMap.get(code).errorMessage;
    }

}
