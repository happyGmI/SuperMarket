package com.xdu.wjw.supermarketserver.invoke.user.service.impl;

import com.xdu.wjw.supermarketmodel.contants.PatternConstant;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertResp;
import com.xdu.wjw.supermarketserver.invoke.user.service.UserService;
import com.xdu.wjw.supermarketserver.util.PatternUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Class: UserServiceImpl
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:37
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserInsertResp register(UserInsertReq userInsertReq) {


        return UserInsertResp.builder().build();
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
        if (!(!StringUtils.isEmpty(email) && PatternUtil.match(PatternConstant.EMAIL_CHECK_PATTERN, email))) {
            throw new IllegalArgumentException();
        }
        if (!(!StringUtils.isEmpty(phone) && PatternUtil.match(PatternConstant.EMAIL_CHECK_PATTERN, phone))) {
            throw new IllegalArgumentException();
        }
    }
}
