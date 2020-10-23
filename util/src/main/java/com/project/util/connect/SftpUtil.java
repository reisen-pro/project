package com.project.util.connect;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Vector;

/**
 * 【类或接口功能描述】
 * 简单的SFTP文件上传类
 * 需要引入jsch依赖
 *
 * @author reisen
 * @version 1.0
 * @date 2020/3/26
 */

@Slf4j
public class SftpUtil {

    /**
     * 文件上传
     *
     * @param file 需要上传的文件
     */
    public void upload(File file) {
        log.info(file.getName() + "开始上传");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            //上传文件
            if (channelSftp == null || !channelSftp.isConnected()) {
                throw new RuntimeException("上传文件" + file.getName()
                        + "时发生异常，channelSftp连接未创建");
            }
            channelSftp.put(in, file.getName());
            log.info("上传成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("没有找到文件");
        } catch (SftpException e) {
            e.printStackTrace();
            log.error("上传文件失败");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件下载
     *
     * @param fileName     需要下载的文件名
     * @param downloadPath 存放的地址
     */
    public void download(String fileName, String downloadPath) {
        log.info(fileName + "开始下载");
        InputStream in;
        FileOutputStream fos;
        try {
            //创建文件
            File downloadFile = new File(downloadPath + File.separator + fileName);
            //输入流
            in = channelSftp.get(fileName);
            //下载文件
            if (channelSftp == null || !channelSftp.isConnected()) {
                throw new RuntimeException("下载文件" + fileName
                        + "时发生异常，channelSftp连接未创建");
            }
            //输出流
            fos = new FileOutputStream(downloadFile);
            //写文件
            int i;
            byte[] bytes = new byte[1024];
            while ((i = in.read(bytes)) != -1) {
                fos.write(bytes, 0, i);
            }
        } catch (SftpException e) {
            log.error("文件下载失败");
        } catch (FileNotFoundException e) {
            log.error("未找到该文件");
        } catch (IOException e) {
            log.error("文件写入失败");
        }
    }

    /**
     * 根据文件名查找文件
     *
     * @param fileName 需要查找的文件名
     * @return 是否存在
     */
    public boolean findFile(String fileName) {
        log.info("开始查找文件");
        boolean findFileFlag = false;
        try {
            //得到所有文件的集合
            Vector<ChannelSftp.LsEntry> ftpFiles = channelSftp.ls(currentPath);
            //查找文件
            if (channelSftp == null || !channelSftp.isConnected()) {
                throw new RuntimeException("查找文件" + fileName
                        + "时发生异常，channelSftp连接未创建");
            }
            //匹配文件名
            for (ChannelSftp.LsEntry ftpFile : ftpFiles) {
                if (ftpFile.getFilename().trim().equals(fileName.trim())) {
                    findFileFlag = true;
                }
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return findFileFlag;
    }

    /**
     * 删除文件
     *
     * @param fileName 需要删除的文件名
     */
    public void deleteFile(String fileName) {
        try {
            //删除文件
            channelSftp.rm(fileName);
            log.info(fileName + "被删除");
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件重命名
     *
     * @param fileName    文件名
     * @param newFileName 文件将要改的名字
     */
    public long renameFile(String fileName, String newFileName) {
        try {
            if (findFile(newFileName)) {
                channelSftp.rename(fileName, newFileName);
                log.info("修改成功");
                return 1;
            } else {
                log.info("这个名称的文件已存在");
                return 2;
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
        log.info("修改失败");
        return 0;
    }


    /**
     * ChannelSftp类实例，信息传输通道
     */
    private ChannelSftp channelSftp;

    /**
     * session类实例，用来持久化连接
     */
    private Session session;

    /**
     * 当前操作路径
     */
    private String currentPath;

    /**
     * 当前目录下文件列表
     */
    @SuppressWarnings("rawtypes")
    private Vector currentFiles;

    /**
     * 取得当前的ChannelSftp实例
     *
     * @return 当前的ChannelSftp实例
     */
    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    /**
     * @param host               服务器地址
     * @param port               端口一般都是22
     * @param username           username
     * @param password           password
     * @param encode             encode default utf-8
     * @param privateKey         私钥
     * @param privateKeyPassword 私钥密码
     */
    public void login(String host, int port, String username, String password, String encode, String privateKey, String privateKeyPassword) {
        log.info("初始化...");
        String location = "/";

        // 默认utf-8
        if (StringUtils.isEmpty(encode)) {
            encode = StandardCharsets.UTF_8.toString();
        }
        // 私钥
        if (!StringUtils.isEmpty(privateKey)) {
            connectServerByPrivateKey(host, port, username, location, encode, privateKey, privateKeyPassword);
        } else {
            connectServer(host, port, username, password, location, encode);
        }
    }

    /**
     * @param server   地址
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     * @param path     路径
     * @param encode   编码
     */
    private void connectServer(String server, int port, String user, String password, String path, String encode) {
        String errorMsg = "";
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, server, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            currentPath = channelSftp.getHome();

            Field f1 = channelSftp.getClass().getDeclaredField("server_version");
            f1.setAccessible(true);
            f1.set(channelSftp, 2);
            channelSftp.setFilenameEncoding(encode);
        } catch (SftpException e) {
            errorMsg = "无法使用SFTP传输文件!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        } catch (JSchException e) {
            errorMsg = "没有权限与SFTP服务器连接!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        } catch (IllegalAccessException e) {
            errorMsg = "无法设置编码格式!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        } catch (NoSuchFieldException e) {
            errorMsg = "无法反射server_version!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 链接远程ftp服务器By秘钥
     *
     * @param server             目标服务器地址
     * @param port               目标服务器端口
     * @param user               用户名
     * @param path               目标路径
     * @param encode             编码
     * @param privateKey         秘钥
     * @param privateKeyPassword 秘钥密码
     */
    public void connectServerByPrivateKey(String server, int port, String user,
                                          String path, String encode, String privateKey,
                                          String privateKeyPassword) {
        String errorMsg = "";

        try {
            JSch jsch = new JSch();

            // 设置密钥和密码
            if (StringUtils.isEmpty(privateKey)) {
                if (StringUtils.isEmpty(privateKeyPassword)) {
                    // 设置带口令的密钥
                    jsch.addIdentity(privateKey, privateKeyPassword);
                } else {
                    // 设置不带口令的密钥
                    jsch.addIdentity(privateKey);
                }
            }

            if (port <= 0) {
                // 连接服务器，采用默认端口
                session = jsch.getSession(user, server);
            } else {
                // 采用指定的端口连接服务器
                session = jsch.getSession(user, server, port);
            }

            // 如果服务器连接不上，则抛出异常
            if (session == null) {
                errorMsg = " 与SFTP服务器连接失败!";
                throw new RuntimeException(errorMsg);
            }

            // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            // 设置登陆超时时间
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.setFilenameEncoding(encode);
            currentPath = channelSftp.getHome();

        } catch (SftpException e) {
            errorMsg = "无法使用SFTP传输文件!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        } catch (JSchException e) {
            errorMsg = "没有权限与SFTP服务器连接!";
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        }

    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
                session.disconnect();
            }
        }
    }

}
