package com.xdu.wjw.supermarketserver;

import com.xdu.wjw.supermarketmodel.enums.user.UserLoginTypeEnum;
import com.xdu.wjw.supermarketmodel.enums.user.UserVerificationTypeEnum;
import com.xdu.wjw.supermarketmodel.model.entity.User;
import com.xdu.wjw.supermarketmodel.model.mapper.UserMapper;
import com.xdu.wjw.supermarketserver.factory.MetaObjectFactory;
import com.xdu.wjw.supermarketserver.invoke.lock.DistributeLockService;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserInsertReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserQueryReq;
import com.xdu.wjw.supermarketserver.invoke.user.service.UserService;
import com.xdu.wjw.supermarketserver.model.dao.user.UserDao;
import com.xdu.wjw.supermarketserver.model.dto.user.UserDto;
import com.xdu.wjw.supermarketserver.util.CacheUtilService;
import com.xdu.wjw.supermarketserver.util.EncryptUtil;
import com.xdu.wjw.supermarketserver.util.JsonUtil;
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
    UserDao userDao;
    @Autowired
    CacheUtilService cacheUtilService;
    @Autowired
    DistributeLockService distributeLockService;
    @Autowired
    UserService userService;
    @Test
    void contextLoads() throws Exception {
//        userDao.insertUser(
//                UserDto
//                .builder().email("1930955585@qq.com")
//                        .phoneNumber(13174530720L)
//                        .autograph(EncryptUtil.Base64Encode("1930955585@qq.com"))
//                        .password(EncryptUtil.Base64Encode("123456"))
//                        .build()
//        );
//        System.out.println(JsonUtil.toJsonString(userMapper.selectByPrimaryKey(1L)));
//        cacheUtilService.setValue(String.valueOf(19829701506L), String.valueOf(123456));
//        System.out.println(cacheUtilService.getValue(String.valueOf(19829701506L)));
//        System.out.println(userService.register(
//                UserInsertReq.builder().phoneNumber(19829701506L).password("123456a").verificationCode("123456").build()
//        ));
//        System.out.println(userService.login(UserQueryReq
//                .builder()
//                .phoneNumber(19829701506L)
//                .password("123456a")
//                .userLoginType(UserLoginTypeEnum.PHONE.getCode())
//                .userVerificationType(UserVerificationTypeEnum.PASSWORD.getCode())
//                .build()));
        long startTime = System.currentTimeMillis();
        System.out.println(JsonUtil.toJsonString(userService.queryUserInfo(UserQueryReq.builder().autograph("MTk4Mjk3MDE1MDY=").build())));
//        System.out.println(JsonUtil.toJsonString(userDao.getUserByAutograph(UserDto.builder().autograph("MTk4Mjk3MDE1MDY=").build())));
        long next = System.currentTimeMillis();
        System.out.println(next - startTime);
        System.out.println(JsonUtil.toJsonString(userService.queryUserInfo(UserQueryReq.builder().autograph("MTk4Mjk3MDE1MDY=").build())));
        System.out.println(System.currentTimeMillis() - next);
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
