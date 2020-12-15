package com.project.cache.client.redis;

import com.project.cache.client.RedisCache;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class RedisCacheProxy implements InvocationHandler {
    private RedisCache cache;

    public RedisCache getInstance(RedisCache cache) {
        this.cache = cache;
        return (RedisCache) Proxy.newProxyInstance(cache.getClass()
                .getClassLoader(), cache.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        method.invoke(cache, args);
        return null;
    }
}
