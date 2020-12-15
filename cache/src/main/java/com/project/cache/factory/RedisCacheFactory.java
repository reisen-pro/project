package com.project.cache.factory;

import com.project.cache.client.RedisCache;
import com.project.cache.dto.CacheConfig;

public interface RedisCacheFactory extends BaseCacheFactory {
    /**
     * 实例化RedisCache缓存-直接连接
     *
     * @param cacheConfig
     * @return
     * @throws Exception
     */
    public RedisCache createRedisCache(CacheConfig cacheConfig) throws Exception;

    /**
     * 实例化RedisCache缓存-哨兵连接
     *
     * @param cacheConfig
     * @return
     * @throws Exception
     */
    public RedisCache createSentinelCache(CacheConfig cacheConfig) throws Exception;

}
