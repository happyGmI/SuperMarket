package com.xdu.wjw.supermarketserver;

import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuperMarketServerApplicationTests {

    @Autowired(required = false)
    UserMapper userMapper;
    @Test
    void contextLoads() {
        userMapper.selectByPrimaryKey(1L);
    }

}
