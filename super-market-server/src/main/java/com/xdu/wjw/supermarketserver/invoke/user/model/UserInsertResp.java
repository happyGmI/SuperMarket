package com.xdu.wjw.supermarketserver.invoke.user.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Class: UserInsertResp
 * @Author: Wei Junwei
 * @Time: 2022/8/14 10:11
 * @Description:
 */
@Data
@Builder
public class UserInsertResp {
    /**
     * 用户token
     */
    private String token;
}
