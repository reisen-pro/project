package com.project.cache.factory.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.project.cache.client.RedisCache;
import com.project.cache.dto.CacheConfig;
import com.project.cache.factory.RedisCacheFactory;
import com.project.cache.jedis.RedisCloudtoLocalSentinelCache;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

@Component
public class RedisCacheFactoryImpl implements RedisCacheFactory {

    @Resource
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public RedisCache createRedisCache(CacheConfig cacheConfig) throws Exception {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(cacheConfig.getMaxIdle());
        poolConfig.setMaxTotal(cacheConfig.getMaxActive());
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        String[] address = StringUtils.split(cacheConfig.getConnectAddress(), ":");
        JedisPool jedisPool = null;
        String host = address[0].trim();
        Integer port = Integer.parseInt(address[1].trim());
        Integer dbNum = ( null == cacheConfig.getDbNum() ? 0 : cacheConfig.getDbNum());
        if (StringUtils.isEmpty(cacheConfig.getPassword())) {
            jedisPool = new JedisPool(poolConfig, host, port,3000, null, dbNum);
        } else {
            jedisPool = new JedisPool(poolConfig, host, port, 3000, cacheConfig.getPassword(),dbNum);
        }
        return new RedisCache(jedisPool);
    }

    @Override
    public RedisCache createSentinelCache(CacheConfig cacheConfig) throws Exception{
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(cacheConfig.getMaxIdle());
        poolConfig.setMaxTotal(cacheConfig.getMaxActive());
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        Integer dbNum = ( null == cacheConfig.getDbNum() ? 0 : cacheConfig.getDbNum());
        JedisSentinelPool sentinelPool = null;
        RedisCloudtoLocalSentinelCache redisCloudtoLocalSentinelCache = null;
        Set<String> sentinels = new HashSet<String>();
        for (String sentinel : StringUtils.split(cacheConfig.getConnectAddress(), ";")) {
            sentinels.add(sentinel.trim());
        }
        String password = StringUtils.isEmpty(cacheConfig.getPassword()) ? null : cacheConfig.getPassword();
        if(!StringUtil.isNullOrEmpty(cacheConfig.getCloudToLocalAddress())){
            redisCloudtoLocalSentinelCache
                    = new RedisCloudtoLocalSentinelCache("mymaster", sentinels, poolConfig,3000,password,dbNum,cacheConfig.getCloudToLocalAddress());
        }else{
            sentinelPool = new JedisSentinelPool("mymaster", sentinels, poolConfig,3000,password,dbNum);
        }
        return Boolean.TRUE.equals(StringUtil.isNullOrEmpty(cacheConfig.getCloudToLocalAddress())) ?
                new RedisCache(sentinelPool):
                new RedisCache(redisCloudtoLocalSentinelCache);
    }

}
