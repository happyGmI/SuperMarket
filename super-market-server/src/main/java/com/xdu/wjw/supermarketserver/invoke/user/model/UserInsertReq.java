package com.xdu.wjw.supermarketserver.invoke.user.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Class: UserInsertReq
 * @Author: Wei Junwei
 * @Time: 2022/8/14 10:11
 * @Description:
 */
@Data
@Builder
public class UserInsertReq {
    /**
     * 使用手机号
     */
    private String autograph;
    /**
     * 用户密码，加密过后
     */
    private String password;
    /**
     * 注册使用的邮箱
     */
    private String email;
    /**
     * 注册使用的手机号码
     */
    private Integer phoneNumber;

}
