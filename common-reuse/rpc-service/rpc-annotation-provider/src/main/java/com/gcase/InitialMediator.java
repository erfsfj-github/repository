package com.gcase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class InitialMediator implements BeanPostProcessor {

    //bean装载完后的事件
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        boolean crs = bean.getClass().isAnnotationPresent(CusRemoteService.class);
        if(crs){
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                //interfaces内部路由
                String key = bean.getClass().getInterfaces()[0].getName()+"."+method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
