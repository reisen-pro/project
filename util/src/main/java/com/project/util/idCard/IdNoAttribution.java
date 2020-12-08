package com.project.util.idCard;

import com.project.util.PropertiesUtil;

import java.util.Properties;

/**
 * 身份证归属地查询
 * 但是信息是从网上找的，不大全。
 */
public class IdNoAttribution {
    public static String getAttribution(String idNo) {
        if (idNo == null || idNo.isEmpty()) {
            return null;
        }
        String code = idNo.substring(0, 6);
        Properties properties = PropertiesUtil.loadProperties("/code.properties");
        System.out.println(properties.get(code));
        return (String) properties.get(code);
    }
    public static void main(String[] args) {
        System.out.println(getAttribution("320323"));
    }
}
