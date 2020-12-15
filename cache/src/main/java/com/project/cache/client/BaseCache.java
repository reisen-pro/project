package com.project.cache.client;

import java.util.Date;

/**
 * @author reisen
 */
public interface BaseCache {
    // 存入缓存
    public boolean put(String key, String namespace, Object value);

    // 从缓存中读取
    public Object get(String key, String namespace);

    // 存入并设置过期时间
    public boolean putAndExpire(String key, String namespace, Object value,Date expire);

    // 根据key删除
    public boolean delete(String key, String namespace);

    // 刷新缓存
    public void refresh(String namespace);

    // 摧毁
    public void destroy() throws Exception;
}
