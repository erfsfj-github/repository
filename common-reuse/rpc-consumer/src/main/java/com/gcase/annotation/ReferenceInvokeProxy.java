package com.gcase.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

//2
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    RemoteInvocationHandler invocationHandler;

    //spring容器初始化之前找到加了自定义注解需要被注入的属性
    //代理这个属性的方法
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields=bean.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(CusReference.class)){
                field.setAccessible(true);
                //针对这个加了CusReference注解的字段，设置为一个代理的值
                Object proxy= Proxy.newProxyInstance(field.getType().getClassLoader(),new Class<?>[]{field.getType()},invocationHandler);
                try {
                    field.set(bean,proxy); //相当于针对加了CusReference的注解，设置了一个代理，这个代理的实现是invocationHandler
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}