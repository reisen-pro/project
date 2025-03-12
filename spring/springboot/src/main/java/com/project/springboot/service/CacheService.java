package com.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveToCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValueFromCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}