package com.gcase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//4、监听远程调用请求（发布远程请求）
public class RpcProxyServer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();


    public void publisher(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8008);
            while (true){
                Socket socket = serverSocket.accept();
                //处理客户端请求
                executorService.execute(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
