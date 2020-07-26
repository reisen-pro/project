package com.project.myutil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Json字符串操作工具类
 */
public class JsonUtil {

    private Map map = new LinkedHashMap();

    private String testJson = "[{\"id\":1,\"username\":\"Reisen\",\"password\":\"1234qwer!\","
            + "\"createDate\":\"2020-05-24 01:46:41\",\"modifyDate\":\"2020-05-24 01:46:46\"}]";

    private String JsonToMap(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr.trim())) {
            jsonStr = testJson;
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
            String[] keyValues = s.split(":");
            map.put(keyValues[0].replace('\"', ' ').trim(), keyValues[1].replace('\"', ' ').trim());
        }
        return "";
    }


    public static void main(String[] args) {
        JsonUtil util = new JsonUtil();
        util.JsonToMap("");
        Map map = util.map;
        System.out.println(map.get("username"));
    }
}
