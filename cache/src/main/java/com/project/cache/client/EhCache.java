package com.project.cache.client;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

import java.util.Date;

public class EhCache implements BaseCache {
    private final Logger logger = Logger.getLogger(getClass());

    private CacheManager cacheManager;

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Object get(String key, String namespace) {
        Element element = getCache(namespace).get(key);
        if (element == null) {
            return null;
        }
        return element.getObjectValue();
    }

    public boolean put(String key, String namespace, Object value) {
        getCache(namespace).put(new Element(key, value));
        return true;
    }

    public void refresh(String namespace) {
        getCache(namespace).removeAll();
    }

    private Cache getCache(String namespace) {
        return cacheManager.getCache(namespace);
    }

    @Override
    public void destroy() throws Exception {
        cacheManager.shutdown();
        logger.info("==============销毁EhCache连接池==============");
    }

    @Override
    public boolean putAndExpire(String key, String namespace, Object value,
                                Date expire) {
        return false;
    }

    @Override
    public boolean delete(String key, String namespace) {
        return false;
    }
}
