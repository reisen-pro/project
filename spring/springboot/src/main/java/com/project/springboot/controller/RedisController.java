package com.project.springboot.controller;

import com.project.springboot.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/getCachedValue")
    public Object getCachedValue(String key) {
        // 尝试从缓存中获取值
        Object value = cacheService.getValueFromCache(key);
        if (value == null) {
            // 缓存中没有找到，执行一些逻辑来获取值
            value = "Some Expensive Operation Result";
            // 将值保存到缓存中
            cacheService.saveToCache(key, value);
        }
        return value;
    }
}
