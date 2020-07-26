package com.project;

import org.springframework.context.annotation.ImportResource;

//用于暴露当前服务的dubbo配置文件
//如果当前项目不需要暴露给其它项目调用，可以不添加
@ImportResource({"classpath:/dubbo/xxx-xxx-provider.xml"})
public class Starter {
}
