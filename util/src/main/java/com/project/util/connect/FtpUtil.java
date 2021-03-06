package com.project.util.connect;

import lombok.Data;
import org.apache.commons.net.ftp.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * ftp封装工具类
 *
 * @author Reisen
 */

@Data
public class FtpUtil {


    /**
     * 上传
     *
     * @param path 路径
     * @param file 文件
     */
    private void upload(String path, File file) throws IOException {
        InputStream in = null;

        // 主动模式（active mode）和被动模式（passive mode）
        // ftp是tcp连接，所以要进行三次握手
        // 主动模式的FTP是指服务器主动连接客户端的数据端口，被动模式的FTP是指服务器被动地等待客户端连接自己的数据端口。
        // 主动FTP对FTP服务器的管理有利，但对客户端的管理不利。因为FTP服务器企图与客户端的高位随机端口建立连接，而这个端口很有可能被客户端的防火墙阻塞掉。
        // 被动FTP对FTP客户端的管理有利，但对服务器端的管理不利。因为客户端要与服务器端建立两个连接，其中一个连到一个高位随机端口，而这个端口很有可能被服务器端的防火墙阻塞掉。
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();

        // 设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

        // 当前所在文件夹
        String nowPath = ftpClient.printWorkingDirectory();

        // 输入目录为空时为默认当前目录
        if (path.length() < 1) {
            path = nowPath;
        }

        // 判断FPT目标文件夹时候存在不存在则创建
        if (!ftpClient.changeWorkingDirectory(path)) {
            ftpClient.makeDirectory(path);
        }

        // 跳转目标目录
        ftpClient.changeWorkingDirectory(path);

        // 上传
        in = new FileInputStream(file);
        String tempName = path + File.separator + file.getName();
        boolean flag = ftpClient.storeFile(new String(tempName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), in);
        if (flag) {
            System.out.print("上传成功");
        } else {
            System.err.print("上传失败");
        }
    }


    /**
     * 下载
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param downPath 保存路径
     */
    public void downLoad(String filePath, String fileName, String downPath) {
        try {
            // 跳转到文件目录
            ftpClient.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftpClient.enterLocalPassiveMode();
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    File downFile = new File(downPath + File.separator
                            + file.getName());
                    OutputStream out = new FileOutputStream(downFile);
                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    boolean flag = ftpClient.retrieveFile(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), out);
                    // 下载成功删除文件,看项目需求
                    // ftp.deleteFile(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
                    out.flush();
                    out.close();
                    if (flag) {
                        System.out.println("下载成功");
                    } else {
                        System.err.println("下载失败");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("下载失败");
        }
    }

    FTPClient ftpClient = null;
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
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


    /**
     * 连接
      * @throws IOException
     */
    private void connect() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(host, port);
    }

    /**
     * 登陆
     * @throws IOException
     */
    private void login() throws IOException {
        ftpClient.login(username, password);
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            System.err.print("未连接到FTP，用户名或密码错误");
            ftpClient.disconnect();
        } else {
            System.out.print("FTP连接成功");
        }
    }

    /**
     * 关闭服务
     */
    private void close() {
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
}
