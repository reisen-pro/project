package com.project.myutil.connectutils;

import lombok.Data;
import org.apache.commons.net.ftp.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * ftp封装工具类
 */

@Data
public class FtpUtil {


    /**
     * 上传
     *
     * @param path 路径
     * @param file 文件
     * @return
     */
    public void upload(String path, File file) throws IOException {

        InputStream in = null;

        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();

        //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

        String nowPath = ftpClient.printWorkingDirectory();

        System.out.println(nowPath);

        //判断FPT目标文件夹时候存在不存在则创建
        if (!ftpClient.changeWorkingDirectory(path)) {
            ftpClient.makeDirectory(path);
        }
        //跳转目标目录
        ftpClient.changeWorkingDirectory(path);

        //上传文件
        in = new FileInputStream(file);
        String tempName = path + File.separator + file.getName();

        boolean flag = ftpClient.storeFile(new String(tempName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), in);

        if (flag) {
            System.out.print("上传成功");
        } else {
            System.err.print("上传失败");
        }

    }


    FTPClient ftpClient = null;
    /*地址*/
    private String host;
    /*端口*/
    private Integer port;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;

    public FtpUtil() {
    }

    /**
     * 构造
     *
     * @param host 地址
     * @param port 端口
     */

    // 构造
    private FtpUtil(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public FtpUtil(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }


    // 连接
    private void connect() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(host, port);
    }

    // 登陆
    private void login(String username, String password) throws IOException {
        ftpClient.login(username, password);
    }

    // 关闭服务
    public void close() {
        try {
            // 登出
            ftpClient.logout();
        } catch (Exception e) {
            System.err.print("FTP关闭失败");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    // 断开连接
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    System.err.print("FTP关闭失败");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FtpUtil ftpUtil = new FtpUtil("192.168.37.129", 21);
        ftpUtil.connect();
        ftpUtil.login("root", "123456");
        File file = new File("D:\\123.jpg");
        ftpUtil.upload("/home",file);
        ftpUtil.close();
    }
}
