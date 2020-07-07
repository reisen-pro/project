package com.project.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * SpringData配置信息
 */
@Configuration
//要扫描的持久层接口及其代理类生产工厂类
@EnableJpaRepositories
//@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, basePackages = {"com.xxx.xxx.dao", "com.xxx.xxx.dao"})
//要扫描的实体类所在的包
@EntityScan(basePackages = "com.xxx.xxx")
public class JpaConfig {
}
