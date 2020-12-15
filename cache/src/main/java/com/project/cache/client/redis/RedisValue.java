package com.project.cache.client.redis;

import redis.clients.jedis.Jedis;

public class RedisValue {
    public boolean put(Jedis jedis, String key, Object value) {
        jedis.set(key, (String) value);
        return true;
    }
}