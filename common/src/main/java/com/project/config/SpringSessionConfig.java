package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * SpringSession配置信息
 */
@Configuration
//用于开启SpringSession的功能
@EnableRedisHttpSession
public class SpringSessionConfig {
}
