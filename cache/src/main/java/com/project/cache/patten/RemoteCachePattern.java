package com.project.cache.patten;

import com.project.cache.client.BaseCache;

import java.util.Date;


public class RemoteCachePattern implements BaseCachePattern {

    private BaseCache remoteCache;

    public void setRemoteCache(BaseCache remoteCache) {
        this.remoteCache = remoteCache;
    }

    public Object get(String key, String namespace) {
        return remoteCache.get(key, namespace);
    }

    public void put(String key, String namespace, Object value) {
        remoteCache.put(key, namespace, value);
    }

    public void refresh(String namespace) {
        remoteCache.refresh(namespace);
    }

    public boolean putAndExpire(String key, String namespace, Object value,
                                Date expire) {
        return remoteCache.putAndExpire(key, namespace, value, expire);
    }

    public boolean delete(String key, String namespace){
        return remoteCache.delete(key, namespace);
    }

    public void destroy() throws Exception {
        remoteCache.destroy();
    }


}
