package com.project.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid数据源相关配置信息
 */
@Configuration
public class DruidConfig {
    /**
     * 用于创建DruidDataSource数据源对象并放入Spring容器
     * 使用配置文件中spring.datasource打头的配置信息来初始化数据源属性
     *
     * @return 创建的DruidDataSource对象
     */
    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}