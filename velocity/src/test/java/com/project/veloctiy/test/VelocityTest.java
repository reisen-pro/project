package com.project.veloctiy.test;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class VelocityTest {

    @Test
    public void test1() throws IOException {
        // 1. 设置velocity的资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 2. 初始化velocity引擎
        Velocity.init(prop);
        // 3. 创建velocity容器
        VelocityContext context = new VelocityContext();
        context.put("name","java");
        // 4. 加载velocity模版文件
        Template template = Velocity.getTemplate("vms/quickStart.vm","utf-8");
        // 5. 合并数据到模版
        String path = "C:\\GitCode\\project\\velocity\\src\\main\\resources\\";
        FileWriter fw = new FileWriter(path + "html\\quickStart.vm.html");
        template.merge(context,fw);
        // 6.释放资源
        fw.close();
    }
}