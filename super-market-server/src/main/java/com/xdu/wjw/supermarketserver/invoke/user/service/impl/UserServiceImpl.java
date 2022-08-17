package com.xdu.wjw.supermarketserver.invoke.user.service.impl;

import com.xdu.wjw.supermarketmodel.contants.PatternConstant;
import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertResp;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserQueryReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserQueryResp;
import com.xdu.wjw.supermarketserver.invoke.user.service.UserService;
import com.xdu.wjw.supermarketserver.model.dao.user.UserDao;
import com.xdu.wjw.supermarketserver.model.dto.user.UserDto;
import com.xdu.wjw.supermarketserver.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class: UserServiceImpl
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:37
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserInsertResp register(UserInsertReq userInsertReq) throws Exception {
        valid(userInsertReq);
        String autograph = "";
        String phoneNumber = userInsertReq.getPhoneNumber() != null ? String.valueOf(userInsertReq.getPhoneNumber()) : "";
        String email = userInsertReq.getEmail();
        if (StringUtils.isNotEmpty(verificationCodeAndEncode(userInsertReq, phoneNumber))) {
            autograph = verificationCodeAndEncode(userInsertReq, phoneNumber);
        }
        if (StringUtils.isNotEmpty(verificationCodeAndEncode(userInsertReq, email))) {
            autograph = verificationCodeAndEncode(userInsertReq, email);
        }
        String password = EncryptUtil.Base64Encode(userInsertReq.getPassword());
        UserDto dto = UserDto.builder()
                .autograph(autograph)
                .email(email)
                .phoneNumber(userInsertReq.getPhoneNumber())
                .password(password)
                .build();
        userDao.insertUser(dto);
        List<User> user;
        user = getUserFromDB(phoneNumber, email);
        // 设置用户缓存
        CacheUtil.getCacheUtil().setValue(autograph, JsonUtil.toJsonString(user.get(0)));
        String token = JwtTokenUtil.getToken(autograph);
        return UserInsertResp.builder()
                .token(token)
                .build();
    }

    public UserQueryResp login(UserQueryReq userQueryReq) {

        return UserQueryResp.builder().build();
    }

    /**
     * 校验验证码
     * @param userInsertReq 用户注册信息
     * @param phoneOrEmail 邮箱/手机
     * @return
     * @throws Exception
     */
    private String verificationCodeAndEncode(UserInsertReq userInsertReq, String phoneOrEmail) throws Exception {
        if (StringUtils.isNotEmpty(phoneOrEmail)) {
            verificationCode(userInsertReq, phoneOrEmail);
            return encodePhoneOrEmail(phoneOrEmail);
        }
        return "";
    }
    private void verificationCode(UserInsertReq userInsertReq, String phoneOrEmail) throws Exception {
        Object verificationCode = CacheUtil.getCacheUtil().getValue(phoneOrEmail);
        if (!userInsertReq.getVerificationCode().equals(verificationCode)) {
            throw new Exception("验证码校验失败！");
        }
        CacheUtil.getCacheUtil().deleteKey(phoneOrEmail);
    }
    private String encodePhoneOrEmail(String phoneOrEmail) {
        return EncryptUtil.Base64Encode(phoneOrEmail);
    }

    private List<User> getUserFromDB(String phoneNumber, String email) throws Exception {
        List<User> user = new ArrayList<>();
        if (StringUtils.isNotEmpty(phoneNumber)) {
            user = userDao.getUserByPhone(Long.valueOf(phoneNumber));
            if (user.size() == 0) {
                throw new Exception("查询结果为空！");
            }
        }
        if (StringUtils.isNotEmpty(email)) {
            user = userDao.getUserByEmail(email);
            if (user.size() == 0) {
                throw new Exception("查询结果为空！");
            }
        }
        user.get(0).setPassword(null);
        user.get(0).setPhoneNumber(null);
        user.get(0).setEmail(null);
        return user;
    }

    private void valid(UserInsertReq userInsertReq) {
        String password = userInsertReq.getPassword();
        String email = userInsertReq.getEmail();
        String phone = String.valueOf(userInsertReq.getPhoneNumber());
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException();
        }
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException();
        }
        if (!(StringUtils.isNotEmpty(email) && PatternUtil.match(PatternConstant.EMAIL_CHECK_PATTERN, email))) {
            throw new IllegalArgumentException();
        }
        if (!(StringUtils.isNotEmpty(phone) && PatternUtil.match(PatternConstant.EMAIL_CHECK_PATTERN, phone))) {
            throw new IllegalArgumentException();
        }
        if (StringUtils.isEmpty(userInsertReq.getVerificationCode())) {
            throw new IllegalArgumentException();
        }
    }

    private void getUserFromCache(String autograph) {

    }

    private String genUserCacheKey(String autograph) {
        return autograph;
    }
}
