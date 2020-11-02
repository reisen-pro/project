package com.project.spring.beans.entity;

import com.project.spring.beans.dao.UserDao;

import java.io.InputStream;
import java.util.Properties;

/**
 * 通过读取文件进行反射调用
 * dao factory
 */
public class DaoFactoryByConfig {
    private UserDao userDao = null;


    private DaoFactoryByConfig() {
        try {
            // 读取配置文件
            InputStream in = DaoFactoryByConfig.class.getClassLoader().getResourceAsStream("dao.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 根据类名实例
            String daoClassName = properties.getProperty("userdao");
            userDao = (UserDao) Class.forName(daoClassName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final DaoFactoryByConfig instance = new DaoFactoryByConfig();

    public static DaoFactoryByConfig getInstance() {
        return instance;
    }

    public UserDao createUserDao() {
        return userDao;
    }
}
