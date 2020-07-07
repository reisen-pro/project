package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;

/**
 * Redis配置信息
 */
@Configuration
public class RedisConfig {



    /**
     * 用于在Spring容器中创建RedisTemplate对象
     * @param redisConnectionFactory Redis连接工厂对象，这里的参数是自动注入的
     * @return 创建的RedisTemplate对象
     */
    @Resource
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}