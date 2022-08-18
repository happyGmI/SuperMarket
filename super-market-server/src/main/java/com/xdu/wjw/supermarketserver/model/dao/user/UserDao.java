package com.xdu.wjw.supermarketserver.model.dao.user;

import com.xdu.wjw.supermarketmodel.enums.user.UserStatusEnum;
import com.xdu.wjw.supermarketmodel.enums.user.UserTypeEnum;
import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketmodel.model.entity.UserExample;
import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.model.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        user.setAutograph(dto.getAutograph());
        user.setType(UserTypeEnum.REGULAR.getCode());
        user.setPassword(dto.getPassword());
        user.setNickname("user");
        user.setPortrait("https://img2.woyaogexing.com/2022/08/02/626ba5f21cb8634d!400x400.jpg");
        user.setStatus(UserStatusEnum.ONLINE.getCode());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
    }
    public List<User> getUserByPhone(UserDto userDto) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneNumberEqualTo(userDto.getPhoneNumber()).andStatusEqualTo(UserStatusEnum.ONLINE.getCode());
        return userMapper.selectByExample(example);
    }
    public List<User> getUserByEmail(UserDto userDto) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(userDto.getEmail()).andStatusEqualTo(UserStatusEnum.ONLINE.getCode());
        return userMapper.selectByExample(example);
    }
    public List<User> getUserByAutograph(UserDto userDto) {
        UserExample example = new UserExample();
        example.createCriteria().andAutographEqualTo(userDto.getAutograph()).andStatusEqualTo(UserStatusEnum.ONLINE.getCode());
        return userMapper.selectByExample(example);
    }
}
