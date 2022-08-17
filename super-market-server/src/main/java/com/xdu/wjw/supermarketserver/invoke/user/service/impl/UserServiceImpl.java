package com.xdu.wjw.supermarketserver.invoke.user.service.impl;

import com.xdu.wjw.supermarketmodel.contants.PatternConstant;
import com.xdu.wjw.supermarketmodel.enums.user.UserLoginTypeEnum;
import com.xdu.wjw.supermarketmodel.enums.user.UserVerificationTypeEnum;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
    @Autowired
    CacheUtilService cacheUtilService;
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserInsertResp register(UserInsertReq userInsertReq) throws Exception {
        validRegisterReq(userInsertReq);
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
        cacheUtilService.setValue(autograph, JsonUtil.toJsonString(user.get(0)));
        String token = JwtTokenUtil.getToken(autograph);
        return UserInsertResp.builder()
                .token(token)
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserQueryResp login(UserQueryReq userQueryReq) throws Exception {
        validLoginReq(userQueryReq);
        String[] verKeyAndValue = getLoginKeyAndValue(userQueryReq).split(":");
        String verKey = verKeyAndValue[0];
        String verValue = verKeyAndValue[1];
        // 扫db判断当前用户是否已经注册了
        List<User> users = getUserFromDB(verKey, verKey);
        User user = users.get(0);
        if (CollectionUtils.isEmpty(users)) {
            throw new Exception("未注册/已注销的用户请先注册");
        }
        // 验证码验证
        if (userQueryReq.getUserVerificationType().equals(UserVerificationTypeEnum.VERIFICATION_CODE.getCode())) {
            verificationCode(verKey, verValue);
        } else if (userQueryReq.getUserVerificationType().equals(UserVerificationTypeEnum.PASSWORD.getCode())) {
            // 加密后比对数据库
            String encodePassword = encodePhoneOrEmail(verKey);
            if (!user.getPassword().equals(encodePassword)) {
                throw new Exception("用户密码不正确");
            }
        }
        // 写缓存
        setUserCache(genUserCacheKey(user.getAutograph()), user);
        // 生成token
        String token = JwtTokenUtil.getToken(user.getAutograph());
        // 返回结果
        // TODO: 邮箱和电话号码脱敏处理
        return UserQueryResp.builder()
                .birthday(user.getBirthday())
                .type(user.getType())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .portrait(user.getPortrait())
                .token(token)
                .build();
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
            verificationCode(userInsertReq.getVerificationCode(), phoneOrEmail);
            return encodePhoneOrEmail(phoneOrEmail);
        }
        return "";
    }
    private void verificationCode(String code, String phoneOrEmail) throws Exception {
        Object verificationCode = cacheUtilService.getValue(phoneOrEmail);
        if (!code.equals(verificationCode)) {
            throw new Exception("验证码校验失败！");
        }
        cacheUtilService.deleteKey(phoneOrEmail);
    }
    private String encodePhoneOrEmail(String phoneOrEmail) {
        return EncryptUtil.Base64Encode(phoneOrEmail);
    }
    private List<User> getUserFromDB(String phoneNumber, String email) throws Exception {
        List<User> user = new ArrayList<>();
        if (StringUtils.isNotEmpty(phoneNumber) && NumberUtils.isDigits(phoneNumber)) {
            user = userDao.getUserByPhone(
                    UserDto.builder()
                    .phoneNumber(Long.valueOf(phoneNumber))
                    .build()
            );
            if (user.size() == 0) {
                throw new Exception("查询结果为空！");
            }
        }
        if (StringUtils.isNotEmpty(email)) {
            user = userDao.getUserByEmail(
                    UserDto.builder()
                        .email(email)
                        .build());
            if (user.size() == 0) {
                throw new Exception("查询结果为空！");
            }
        }

        return user;
    }
    private void validRegisterReq(UserInsertReq userInsertReq) {
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
    private void validLoginReq(UserQueryReq userQueryReq) {
        String password = userQueryReq.getPassword();
        String email = userQueryReq.getEmail();
        String phone = String.valueOf(userQueryReq.getPhoneNumber());
        String code = userQueryReq.getVerificationCode();
        Integer loginType = userQueryReq.getUserLoginType();
        Integer verificationType = userQueryReq.getUserVerificationType();
        if (StringUtils.isEmpty(password) && verificationType.equals(UserVerificationTypeEnum.PASSWORD.getCode())) {
            throw new IllegalArgumentException();
        }
        if (StringUtils.isEmpty(code) && verificationType.equals(UserVerificationTypeEnum.VERIFICATION_CODE.getCode())) {
            throw new IllegalArgumentException();
        }
        if (StringUtils.isEmpty(email) && loginType.equals(UserLoginTypeEnum.EMAIL.getCode())) {
            throw new IllegalArgumentException();
        }
        if (StringUtils.isEmpty(phone) && verificationType.equals(UserLoginTypeEnum.PHONE.getCode())) {
            throw new IllegalArgumentException();
        }
    }
    private String getLoginKeyAndValue(UserQueryReq userQueryReq) {
        String password = userQueryReq.getPassword();
        String email = userQueryReq.getEmail();
        String phone = String.valueOf(userQueryReq.getPhoneNumber());
        String code = userQueryReq.getVerificationCode();
        Integer loginType = userQueryReq.getUserLoginType();
        Integer verificationType = userQueryReq.getUserVerificationType();
        String verificationKey = "";
        String verificationValue = "";
        if (!StringUtils.isEmpty(password) && verificationType.equals(UserVerificationTypeEnum.PASSWORD.getCode())) {
            // 使用密码进行验证
            verificationValue = password;
        }
        if (!StringUtils.isEmpty(code) && verificationType.equals(UserVerificationTypeEnum.VERIFICATION_CODE.getCode())) {
            // 使用验证码进行验证
            verificationValue = code;
        }
        if (!StringUtils.isEmpty(email) && loginType.equals(UserLoginTypeEnum.EMAIL.getCode())) {
            // 使用手机号进行验证
            verificationKey = email;
        }
        if (!StringUtils.isEmpty(phone) && verificationType.equals(UserLoginTypeEnum.PHONE.getCode())) {
            // 使用手机号进行验证
            verificationKey = phone;
        }
        String verificationFormat = "%s:%s";
        return String.format(verificationFormat, verificationKey, verificationValue);
    }

    private User getUserFromCache(String autograph) {
        return JsonUtil.jsonStringToObject(
                String.valueOf(cacheUtilService.getValue(genUserCacheKey(autograph))),
                User.class);
    }

    private String genUserCacheKey(String autograph) {
        return autograph;
    }
    private void setUserCache(String key, User user) {
        user.setPassword(null);
        user.setPhoneNumber(null);
        user.setEmail(null);
        cacheUtilService.setValue(key, JsonUtil.toJsonString(user));
    }
}
