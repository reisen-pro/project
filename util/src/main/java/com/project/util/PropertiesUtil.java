package com.project.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Properties文件操作相关的工具类
 *
 * @author Reisen
 */
public class PropertiesUtil {
    /**
     * 私有构造
     */
    private PropertiesUtil() {
    }

    public static Properties loadProperties(String filePath) {
        //Properties继承自Hashtable，所以其中的结构就是K-V对结构
        Properties properties = new Properties();
        try {
            if (filePath.startsWith("/")) {
                properties.load(new InputStreamReader(PropertiesUtil.class.getResourceAsStream(filePath), StandardCharsets.UTF_8));
            } else {
                FileInputStream fis = new FileInputStream(filePath);
                properties.load(fis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties loadProperties(InputStream in) {
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
