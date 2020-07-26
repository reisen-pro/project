package com.project.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 请求的客户端
 */
public class TimeClient {
    public static void main(String[] args) {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client = null;

        try {
            // 创建一个客户端
            client = new Socket("127.0.0.1", 8000);
            // 客户端的输出流
            writer = new PrintWriter(client.getOutputStream());
            // 从客户端得到的buffer缓冲流
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while (true) {
                writer.println("GET CURRENT TIME");
                // 刷新
                writer.flush();
                // 读取服务器数据，读取一行
                String response = reader.readLine();
                System.out.println("Current Time:" + response);
                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
