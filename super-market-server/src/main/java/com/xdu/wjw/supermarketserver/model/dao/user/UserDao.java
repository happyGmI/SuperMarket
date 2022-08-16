package com.xdu.wjw.supermarketserver.model.dao.user;

import com.xdu.wjw.supermarketmodel.enums.user.UserStatusEnum;
import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketmodel.model.entity.UserExample;
import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import com.xdu.wjw.supermarketserver.model.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void insertUser(UserDto dto) {
        User user = new User();
        userMapper.insert(user);
    }
    public List<User> getUserByPhone(Long phone) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneNumberEqualTo(phone).andStatusEqualTo(UserStatusEnum.ONLINE.getCode());
        return userMapper.selectByExample(example);
    }
    public List<User> getUserByEmail(String email) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email).andStatusEqualTo(UserStatusEnum.ONLINE.getCode());
        return userMapper.selectByExample(example);
    }

}
