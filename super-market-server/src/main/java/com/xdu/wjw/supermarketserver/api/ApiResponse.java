package com.xdu.wjw.supermarketserver.api;

import com.xdu.wjw.supermarketmodel.enums.api.ApiResponseEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @Class: ApiResponse
 * @Author: Wei Junwei
 * @Time: 2022/8/14 22:55
 * @Description:
 */
@Builder
@Data
public class ApiResponse<T> {

    private Integer status;

    private Integer errorCode;

    private String errorMessage;

    private T data;

    public static ApiResponse<Object> buildSuccessEmptyResponse() {
        return ApiResponse.builder()
                .status(ApiResponseEnum.getStatusByCode(0))
                .errorCode(ApiResponseEnum.getErrorCodeByCode(0))
                .errorMessage(ApiResponseEnum.getErrorMessageByCode(0))
                .build();
    }
    public static <T> ApiResponse<Object> buildSuccessResponse(T data) {
        return ApiResponse.builder()
                .status(ApiResponseEnum.getStatusByCode(0))
                .errorCode(ApiResponseEnum.getErrorCodeByCode(0))
                .errorMessage(ApiResponseEnum.getErrorMessageByCode(0))
                .data(data)
                .build();
    }
    public static ApiResponse<Object> buildSuccessPageResponse(ApiPageResponse data) {
        return ApiResponse.builder()
                .status(ApiResponseEnum.getStatusByCode(0))
                .errorCode(ApiResponseEnum.getErrorCodeByCode(0))
                .errorMessage(ApiResponseEnum.getErrorMessageByCode(0))
                .data(data)
                .build();
    }
    public static ApiResponse<Object> buildFailResponse(ApiErrorShowResponse response) {
        return ApiResponse.builder()
                .status(ApiResponseEnum.getStatusByCode(response.getCode()))
                .errorCode(ApiResponseEnum.getErrorCodeByCode(response.getCode()))
                .errorMessage(ApiResponseEnum.getErrorMessageByCode(response.getCode()))
                .data(response)
                .build();
    }
}
