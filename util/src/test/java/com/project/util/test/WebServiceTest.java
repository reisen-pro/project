package com.project.util.test;


import com.project.util.connect.WebServiceUtil;
import org.junit.Test;

public class WebServiceTest {


    /**
     * 可以在demo下启动模拟的一个简单输入城市得到天气的服务。
     */
    @Test
    public void doTest() {
        Object result[] = WebServiceUtil.invoke("http://127.0.0.1:12345/weather", "queryWeather", "上海");
        System.out.println(result[0]);
    }
}
