package com.xdu.wjw.supermarketserver;

import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.factory.MetaObjectFactory;
import com.xdu.wjw.supermarketserver.invoke.lock.DistributeLockService;
import com.xdu.wjw.supermarketserver.util.CacheUtilService;
import com.xdu.wjw.supermarketserver.util.LogUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.util.resources.cldr.ss.CalendarData_ss_SZ;

import java.util.UUID;

@SpringBootTest
class SuperMarketServerApplicationTests {

    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    CacheUtilService cacheUtilService;
    @Autowired
    DistributeLockService distributeLockService;
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
    @Test
    public void testLock() throws Exception{
        UUID uuid = UUID.randomUUID();
        distributeLockService.acquireLock(String.valueOf(uuid), 60);
        System.out.println(cacheUtilService.getValue(String.valueOf(uuid)));
        Thread.sleep(10000);
        System.out.println(distributeLockService.acquireLock(String.valueOf(uuid), 60));
        System.out.println(cacheUtilService.getExpertSeconds(String.valueOf(uuid)));
        System.out.println(cacheUtilService.getValue(String.valueOf(uuid)));
        distributeLockService.releaseLock(String.valueOf(uuid));
        System.out.println(cacheUtilService.getValue(String.valueOf(uuid)));
    }

    @Test
    public void getCacheUtilService() {
        MetaObjectFactory factory = new MetaObjectFactory();
        MetaObject metaObject = factory.buildFlowMetaObject(User.class);
        System.out.println(metaObject.getObjectWrapper());
    }

    public static void main(String[] args) {
        MetaObjectFactory factory = new MetaObjectFactory();
        Class userClass = User.class;
        MetaObject metaObject = factory.buildFlowMetaObject(User.class);
        Object object = metaObject.getOriginalObject();
        System.out.println(object.toString());
        System.out.println(metaObject.getObjectWrapper());
    }
}
