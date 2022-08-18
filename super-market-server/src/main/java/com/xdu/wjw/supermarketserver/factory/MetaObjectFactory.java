package com.xdu.wjw.supermarketserver.factory;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @author weijunwei
 */
public class MetaObjectFactory {
    /**
     * 对象工厂
     */
    private ObjectFactory objectFactory;
    /**
     * 对象封装工厂
     */
    private ObjectWrapperFactory objectWrapperFactory;
    /**
     * 反射器工厂
     */
    private ReflectorFactory reflectorFactory;

    public MetaObjectFactory() {
        this.objectFactory = new DefaultObjectFactory();
        this.objectWrapperFactory = new DefaultObjectWrapperFactory();
        this.reflectorFactory = new DefaultReflectorFactory();
    }
    public MetaObjectFactory(ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
        this.objectFactory = objectFactory;
        this.objectWrapperFactory = objectWrapperFactory;
        this.reflectorFactory = reflectorFactory;
    }

    /**
     * 生成MetaObject对象
     * @param type
     * @return
     */
    public MetaObject buildFlowMetaObject(Class type) {
        if (type == null) {
            return null;
        }
        Object realObject = objectFactory.create(type);
        return MetaObject.forObject(realObject, objectFactory, objectWrapperFactory, reflectorFactory);
    }

    public MetaObject buildFlowMetaObject(Object realObject) {
        if (realObject == null) {
            return null;
        }
        return MetaObject.forObject(realObject, objectFactory, objectWrapperFactory, reflectorFactory);
    }
}
