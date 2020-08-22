package com.project.myutil.connect;

import lombok.Data;

import java.io.File;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import com.project.myutil.PropertiesUtil;

/**
 * 数据库操作工具类：封装连接的获取以及资源的释放等通用操作
 *
 * @author Reisen
 */
@Data
public class DbUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClass;

    private DbUtil() {
    }

    static {
        Properties props = PropertiesUtil.loadProperties("resource" + File.separator + "jdbc.properties");
        try {
            Class.forName(props.getProperty("driverClass"));
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败...");
            e.printStackTrace();
        }
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }


    static {

        // 获取ResourceBundle ctrl+2 l
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

        // 获取指定的内容
        driverClass = bundle.getString("driverClass");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        url = bundle.getString("url");
        username = bundle.getString(username);
        password = bundle.getString(password);
    }

    /**
     * 用来获取连接对象的方法
     *
     * @return 连接对象
     */
    public static synchronized Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     *
     * @param conn 连接
     * @param st   语句执行者
     * @param rs   结果集
     */
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
        closeConn(conn);
    }

    /**
     * 释放连接
     *
     * @param conn 连接
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }

    }

    /**
     * 释放语句执行者
     *
     * @param st 语句执行者
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }

    }

    /**
     * 释放结果集
     *
     * @param rs 结果集
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }

    }

}
