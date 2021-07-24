package com.zuzuche.orderservice;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 *
 * @author Billy
 * @date 2021-07-23 22:30
 */
public class SocketClientDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        //socket.connect();
        OutputStream socketOutputStream = socket.getOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream("C:\\Users\\Lenovo\\Desktop\\new 6.txt"));
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            socketOutputStream.write(bytes, 0, len);
        }
        // 写结束标记
        socket.shutdownOutput();
        // 获取输入了
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream buf = new BufferedInputStream(inputStream);
        // 打印服务器返回的数据
        while ((len = buf.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        socket.close();
    }
}
