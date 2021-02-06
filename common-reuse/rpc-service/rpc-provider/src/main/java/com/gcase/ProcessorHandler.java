package com.gcase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
//5、具体处理客户端请求过程
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    //处理客户端调用请求
    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object result = invoke(rpcRequest);
            System.out.println("服务端的执行结果："+result);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //将方法返回的数据写回客户端
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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

    //执行客户端传过来需要调用的方法
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过反射进行服务的调用
        Class clazz = Class.forName(rpcRequest.getClassName());
        //找到目标方法
        Method method = clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getTypes());
        return method.invoke(service, rpcRequest.getArgs());
    }
}
