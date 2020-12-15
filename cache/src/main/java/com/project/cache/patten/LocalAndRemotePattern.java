package com.project.cache.patten;

import com.project.cache.client.BaseCache;

public class LocalAndRemotePattern implements BaseCachePattern {

    private BaseCache localCache;

    private BaseCache remoteCache;

    public void setLocalCache(BaseCache localCache) {
        this.localCache = localCache;
    }

    public void setRemoteCache(BaseCache remoteCache) {
        this.remoteCache = remoteCache;
    }

    public Object get(String key, String namespace) {
        Object result = localCache.get(key, namespace);
        if (result == null) {
            System.out.println("localCache not exists");
            result = remoteCache.get(key, namespace);
            if (result != null) {
                localCache.put(key, namespace, result);
                System.out.println("localCache add");
            }
        }
        return result;
    }

    public void put(String key, String namespace, Object value) {
        localCache.put(key, namespace, value);
        remoteCache.put(key, namespace, value);
    }

    public void refresh(String namespace) {
        remoteCache.refresh(namespace);
        localCache.refresh(namespace);
    }

}
