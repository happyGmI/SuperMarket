package com.xdu.wjw.supermarketserver;

import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.factory.MetaObjectFactory;
import com.xdu.wjw.supermarketserver.invoke.lock.DistributeLockService;
import com.xdu.wjw.supermarketserver.util.CacheUtilService;
import org.apache.ibatis.reflection.MetaObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MetaObjectFactory factory = new MetaObjectFactory();
        MetaObject metaObject = factory.buildFlowMetaObject(Class.forName("java.lang.String"));
        Object o = metaObject.getOriginalObject();
        System.out.println(o);
        Class<?> clazz = Class.forName("com.xdu.wjw.supermarketserver.util.TestRefil");
        Method[] methods = clazz.getMethods();
        Map<String, Map<String, Object>> request = new HashMap<>();
        HashMap<String, Object> heheRequest = new HashMap<>();
        heheRequest.put("hehe", "meituan");
        heheRequest.put("nihao", "weijunwei");
        request.put("nihao", heheRequest);
        request.put("hehe", new HashMap<>());
        Object o1 = clazz.newInstance();
        for (Method method : methods) {
            System.out.println(method);
            Map<String, Object> param = request.get(method.getName());
            if (!CollectionUtils.isEmpty(param)) {
                Object[] array = param.values().toArray();
                for (Object o2 : array) {
                    System.out.println(o2);
                }
                method.invoke(o1, array);
            }
        }
    }
}
