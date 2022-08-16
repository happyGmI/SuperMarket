package com.xdu.wjw.supermarketserver.model.dao.user;

import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Class: UserDao
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:54
 * @Description:
 */
@Repository
public class UserDao {

    @Autowired(required = false)
    UserMapper userMapper;

    public void insertUser(UserInsertReq userInfo) {

    }

}
