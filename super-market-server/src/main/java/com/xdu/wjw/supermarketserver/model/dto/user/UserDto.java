package com.xdu.wjw.supermarketserver.model.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * @Class: UserDto
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:54
 * @Description:
 */
@Builder
@Data
public class UserDto {
    /**
     * 使用手机号生成的签名
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
    private Long phoneNumber;
}
