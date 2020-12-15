package com.project.cache.patten;

public interface BaseCachePattern {
    public void put(String key, String namespace, Object value);

    public Object get(String key, String namespace);

    public void refresh(String namespace);
}
