package com.project.myutil;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Properties文件操作相关的工具类
 *
 * @author Reisen
 */
public class PropertiesUtil {
    private PropertiesUtil() {

    }

    public static Properties loadProperties(String filePath) {
        //Properties继承自Hashtable，所以其中的结构就是K-V对结构
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath);) {
            //读取properties配置文件来为Properties对象设置键值对
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
