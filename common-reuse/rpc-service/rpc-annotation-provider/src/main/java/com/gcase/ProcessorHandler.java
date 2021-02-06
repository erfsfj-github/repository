package com.gcase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//5、具体处理客户端请求过程
public class ProcessorHandler implements Runnable {
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    //处理客户端调用请求
    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object result = Mediator.getInstance().processor(request);
            System.out.println("服务端的执行结果："+result);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            /** 将方法返回的数据写回客户端 **/
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
