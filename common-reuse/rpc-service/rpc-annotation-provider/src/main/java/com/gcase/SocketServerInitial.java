package com.gcase;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    //Spring容器启动完成后触发事件
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动服务
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8008);
            while (true){
                Socket socket = serverSocket.accept();
                //处理客户端请求
                executorService.execute(new ProcessorHandler(socket));
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
