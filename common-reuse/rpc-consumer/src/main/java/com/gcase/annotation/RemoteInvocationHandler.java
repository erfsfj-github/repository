package com.gcase.annotation;

import org.example.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//2、动态代理具体实现
@Component
public class
RemoteInvocationHandler implements InvocationHandler {
    @Value("${remote.host}")
    private String host;
    @Value("${remote.port}")
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RemoteInvocationHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //建立远程连接
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);
        /*******整合调用的具体方法所需要的信息 RpcRequest*******/
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setArgs(args);//实际参数 []
        System.out.println("Object[] args"+args);
        rpcRequest.setClassName(method.getDeclaringClass().getName());//类型全路径
        System.out.println("ClassName = "+method.getDeclaringClass().getName());

        rpcRequest.setTypes(method.getParameterTypes());//参数类型 []
        System.out.println("Class[] types = "+method.getParameterTypes().toString());
        rpcRequest.setMethodName(method.getName());//方法名称
        System.out.println("MethodName = "+method.getName());
        return rpcNetTransport.send(rpcRequest);
    }
}
