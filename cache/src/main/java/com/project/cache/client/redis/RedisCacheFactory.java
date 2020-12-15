package com.project.cache.client.redis;

import javax.annotation.Resource;

import com.project.cache.client.RedisCache;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisSentinelPool;


@Component
public class RedisCacheFactory implements BeanFactoryAware {

    @Resource
    private BeanFactory beanFactory;

    @Resource
    private RedisCacheProxy proxy;

    public RedisCache createRadisClient(JedisSentinelPool pool) {
        return new RedisCache(pool);
        // return proxy.getInstance(cache);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
