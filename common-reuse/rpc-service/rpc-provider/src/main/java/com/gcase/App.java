package com.gcase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //创建服务
        OrderServiceImpl orderService = new OrderServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        //发布服务
        rpcProxyServer.publisher(orderService,8008);
    }
}
