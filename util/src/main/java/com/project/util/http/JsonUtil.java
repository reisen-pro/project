package com.project.util.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Json字符串操作工具类
 * @author Reisen
 */
public class JsonUtil {

    private Map map = new LinkedHashMap();

    private String JsonToMap(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr.trim())) {
            return "";
        }
        //去头
        for (int i = 0; i < jsonStr.getBytes().length; i++) {
            byte[] bytes = jsonStr.getBytes();
            if ('\"' == (bytes[i])) {
                jsonStr = jsonStr.substring(i);
                break;
            }
        }
        //去尾
        for (int i = jsonStr.getBytes().length - 1; i > 0; i--) {
            byte[] bytes = jsonStr.getBytes();
            if ('\"' == (bytes[i])) {
                jsonStr = jsonStr.substring(0, i + 1);
                break;
            }
        }

        String[] jsonArray = jsonStr.split(",");
        for (String s : jsonArray) {
            String[] keyValues = s.split(":", 2);
            map.put(keyValues[0].replace('\"', ' ').trim(), keyValues[1].replace('\"', ' ').trim());
        }
        return "";
    }
}
