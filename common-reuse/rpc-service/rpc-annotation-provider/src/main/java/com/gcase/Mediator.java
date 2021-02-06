package com.gcase;

import com.gcase.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//2
public class Mediator {
    //需要被发布的服务（bean实例）
    public static Map<String,BeanMethod> map = new ConcurrentHashMap<>();

    private volatile static Mediator instance;
    private Mediator(){

    }
    public static Mediator getInstance(){
        if(instance==null){
            synchronized(Mediator.class){
                if(instance==null){
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest request){
        String key = request.getClassName()+"."+request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if(beanMethod==null){
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean,request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
