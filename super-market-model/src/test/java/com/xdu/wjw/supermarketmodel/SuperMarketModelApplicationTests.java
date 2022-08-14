package com.xdu.wjw.supermarketmodel;

import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuperMarketModelApplicationTests {

    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    void contextLoads() {
        userMapper.selectByPrimaryKey(1L);
    }

}
