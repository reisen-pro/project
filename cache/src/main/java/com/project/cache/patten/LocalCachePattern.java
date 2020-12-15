package com.project.cache.patten;

import com.project.cache.client.BaseCache;

public class LocalCachePattern implements BaseCachePattern {

    private BaseCache localCache;

    public void setLocalCache(BaseCache localCache) {
        this.localCache = localCache;
    }

    public Object get(String key, String namespace) {
        return localCache.get(key, namespace);
    }

    public void put(String key, String namespace, Object value) {
        localCache.put(key, namespace, value);
    }

    public void refresh(String namespace) {
        localCache.refresh(namespace);
    }

}
