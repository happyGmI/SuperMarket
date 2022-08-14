package com.xdu.wjw.supermarketserver.api;

import lombok.Data;

import java.util.List;

/**
 * @Class: ApiListResponse
 * @Author: Wei Junwei
 * @Time: 2022/8/15 0:11
 * @Description:
 */
@Data
public class ApiPageResponse<T> {

    private Integer total;
    private List<T> data;

    public static<T> ApiPageResponse<T> buildApiPageResponse (List<T> data) {
        ApiPageResponse<T> response = new ApiPageResponse<>();
        response.setData(data);
        response.setTotal(data.size());
        return response;
    }
}
