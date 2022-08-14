package com.xdu.wjw.supermarketserver.invoke.user.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Class: UserReq
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:34
 * @Description:
 */
@Data
@Builder
public class UserReq {

    /**
     * 使用手机号/邮箱生成的用户签名
     */
    private String autograph;
}
