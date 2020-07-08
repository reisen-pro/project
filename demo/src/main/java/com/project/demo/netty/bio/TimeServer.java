package com.project.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


/**
 * 时间服务器
 */
public class TimeServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            // 开启一个Socket服务 端口8000
            server = new ServerSocket(8000);
            System.out.println("TimeServer start on 8000");
            while (true) {
                // 接受返回的client
                Socket client = server.accept();
                // 启动线程
                TimeServerHandler handler = new TimeServerHandler(client);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TimeServerHandler implements Runnable {
        private Socket clientProxy;

        public TimeServerHandler(Socket clientProxy) {
            this.clientProxy = clientProxy;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                // 读取流
                reader = new BufferedReader(new InputStreamReader(clientProxy.getInputStream()));
                // 打印流
                writer = new PrintWriter((clientProxy.getOutputStream()));
                while (true){
                    // 读取一行
                    String request = reader.readLine();
                    if(!"GET CURRENT TIME".equals(request)){
                        writer.println("BAD_REQUEST");
                    }else {
                        // 打印系统本土时间
                        writer.println(Calendar.getInstance().getTime().toLocaleString());
                    }
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    reader.close();
                    clientProxy.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
