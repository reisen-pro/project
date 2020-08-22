package com.project.myutil.connectutils;

import com.jcraft.jsch.*;

import java.util.Properties;

/**
 * sftp操作工具类
 * 需要引入 jsch
 */
public class SFTPUtil {

    private ChannelSftp sftp;
    private Session session;


    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 私钥 */
    private String privateKey;
    /* 服务器地址 */
    private String host;
    /* 端口 */
    private int port;

    public SFTPUtil(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }


}