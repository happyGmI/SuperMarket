package com.xdu.wjw.supermarketserver.api;

import lombok.Builder;
import lombok.Data;

/**
 * @Class: ApiErrorShowResponse
 * @Author: Wei Junwei
 * @Time: 2022/8/15 0:00
 * @Description:
 */
@Builder
@Data
public class ApiErrorShowResponse {

    private static Integer INTERN_ERROR_CODE = 1;
    private static Integer INVALID_ERROR_CODE = 2;
    private static String INTERN_ERROR = "对不起，服务器开了小差请稍后重试";
    private static String INVALID_ERROR = "对不起，请检查您的参数是否正确";

    private Integer code;
    private String showMessage;

    public static ApiErrorShowResponse buildShowResponse(Integer code, String showMessage) {
        return ApiErrorShowResponse.builder()
                .code(code)
                .showMessage(showMessage)
                .build();
    }
}
