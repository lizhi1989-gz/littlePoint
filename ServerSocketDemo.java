package com.zuzuche.orderservice;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * tcpdemo
 *
 * @author Billy
 * @date 2021-07-23 15:31
 */
public class ServerSocketDemo {

    public static void main(String[] args) {
        new Thread(()->{
            try {
                method();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();



    }

    public static void method() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        // 创建日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmmSS");
        //创建缓冲流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        //本地缓冲输出流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\" + sdf.format(new Date()) + ".txt")
        );
        byte[] bytes = new byte[1024 * 1024];
        while(inputStream.read(bytes)!=-1) {
            bufferedOutputStream.write(bytes);
        }
        //获取输出流 给客户端写数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好".getBytes());
        outputStream.close();
        bufferedInputStream.close();
        socket.close();
    }
}
