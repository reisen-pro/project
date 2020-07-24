package com.project.myutil.connectutils;

import org.apache.commons.net.ftp.*;


/**
 * ftp封装工具类
 */
public class FtpUtil {


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
     * 地址端口构造
     *
     * @param host 地址
     * @param port 端口
     */
    public FtpUtil(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public FtpUtil(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
