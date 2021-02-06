package com.gcase.normal;

import com.gcase.IOrderService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Rpc客户端请求代理对象
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        //客户端调用远程服务
        IOrderService orderService = rpcProxyClient.clientProxy(IOrderService.class,"localhost",8008);
        String s = orderService.helloWorld();
        System.out.println(s);
        orderService.orderById("id");
    }
}
