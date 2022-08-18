package com.xdu.wjw.supermarketserver.invoke.user.service;

import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertResp;

/**
 * @Class: UserServiceImpl
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:36
 * @Description:
 */
public interface UserService {
    /**
     * 用户注册接口
     * @param userInsertReq
     * @return
     * @throws Exception
     */
    UserInsertResp register(UserInsertReq userInsertReq) throws Exception;

}
