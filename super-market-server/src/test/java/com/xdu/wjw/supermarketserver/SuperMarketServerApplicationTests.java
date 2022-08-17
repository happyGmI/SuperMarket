package com.xdu.wjw.supermarketserver;

import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.util.CacheUtilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuperMarketServerApplicationTests {

    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    CacheUtilService cacheUtilService;
    @Test
    void contextLoads() {
        userMapper.selectByPrimaryKey(1L);
    }
    @Test
    public void testCache() {
        cacheUtilService.setValue("hello", "world");
        System.out.println(cacheUtilService.getValue("hello"));
        cacheUtilService.deleteKey("hello");
        System.out.println(cacheUtilService.getValue("hello"));
    }

}
