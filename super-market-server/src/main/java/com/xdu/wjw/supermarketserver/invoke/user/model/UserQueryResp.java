package com.xdu.wjw.supermarketserver.invoke.user.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Class: UserQueryResp
 * @Author: Wei Junwei
 * @Time: 2022/8/14 12:06
 * @Description:
 */
@Data
@Builder
public class UserQueryResp {
    /**
     * 使用手机号/邮箱用户签名
     */
    private String autograph;
    /**
     * 用户类型
     */
    private Byte type;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String portrait;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 脱敏处理后的邮箱
     */
    private String email;
    /**
     * 脱敏处理后的电话号码
     */
    private String phoneNumber;
    /**
     * token
     */
    private String token;
}
