package com.xdu.wjw.supermarketserver.invoke.user.model;

import com.xdu.wjw.supermarketmodel.enums.user.UserLoginTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @Class: UserQueryReq
 * @Author: Wei Junwei
 * @Time: 2022/8/14 12:05
 * @Description:
 */
@Data
@Builder
public class UserQueryReq {
    /**
     * 用户密码原始密码
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
    /**
     * 验证码
     */
    private String verificationCode;
    /**
     * LoginType
     */
    private UserLoginTypeEnum userLoginType;
}
