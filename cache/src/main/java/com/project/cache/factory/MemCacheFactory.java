package com.project.cache.factory;

import com.project.cache.client.MemCache;
import com.project.cache.dto.CacheConfig;
import org.springframework.beans.factory.BeanFactoryAware;

public interface MemCacheFactory extends BeanFactoryAware {
    /**
     * 实例化MemCache缓存
     * @param cacheConfig
     * @return
     * @throws Exception
     */
    public MemCache createMemCache(CacheConfig cacheConfig) throws Exception;
}
