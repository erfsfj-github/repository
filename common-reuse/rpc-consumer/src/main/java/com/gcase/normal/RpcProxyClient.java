package com.gcase.normal;

import java.lang.reflect.Proxy;
//1、客户端的动态代理，接收远程调用到的对象
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaces,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class<?>[]{interfaces},
                new RemoteInvocationHandler(host,port));
    }
}
