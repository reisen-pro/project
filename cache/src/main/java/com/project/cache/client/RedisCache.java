package com.project.cache.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.cache.constant.OperateType;
import com.project.cache.dto.CacheOperate;
import com.project.cache.jedis.RedisCloudtoLocalSentinelCache;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.util.Pool;

public class RedisCache implements BaseCache {

    private static final Logger logger = Logger.getLogger(RedisCache.class);

    private Pool<Jedis> jedisPool;

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    public RedisCache(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisCache(JedisSentinelPool pool) {
        this.jedisPool = pool;
    }

    public RedisCache(RedisCloudtoLocalSentinelCache pool) {
        this.jedisPool = pool;
    }

    public void operValue() {
    }

    @Override
    public boolean put(String key, String namespace, Object value) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.set(cacheKey(key, namespace), (String) value);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            throw e;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作put, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    @Override
    public Object get(String key, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.get(cacheKey(key, namespace));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作get, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean hput(String key, String field, String namespace, Object value) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hset(cacheKey(key, namespace), field, (String) value);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作hput, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean hdel(String key, String namespace, String... field) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hdel(cacheKey(key, namespace), field);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作hdel, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public String hget(String key, String field, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hget(cacheKey(key, namespace), field);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作hget, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Map<String, String> hgetAll(String key, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hgetAll(cacheKey(key, namespace));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作hgetall, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Long hincrBy(String key, String namespace, String field, Long value) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hincrBy(cacheKey(key, namespace), field, value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作hincrBy, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean zadd(String key, String namespace, double score, String member) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.zadd(cacheKey(key, namespace), score, member);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zadd, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean zadd(String key, String namespace, Map<String, Double> members) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.zadd(cacheKey(key, namespace), members);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zadd, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean zrem(String key, String namespace, String... members) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.zrem(cacheKey(key, namespace), members);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrem, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Set<String> zrange(String key, String namespace, long start,
                              long end, boolean isDesc) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            if (isDesc) {
                return jedis.zrevrange(cacheKey(key, namespace), start, end);
            } else {
                return jedis.zrange(cacheKey(key, namespace), start, end);
            }
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrange, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    public Set<String> zrangeByScore(String key, String namespace, double min,
                                     double max, boolean isDesc) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            if (isDesc) {
                return jedis.zrevrangeByScore(cacheKey(key, namespace), max, min);
            } else {
                return jedis.zrangeByScore(cacheKey(key, namespace), min, max);
            }
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrangeByScore, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean zremrange(String key, String namespace, long start, long end) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.zremrangeByRank(cacheKey(key, namespace), start, end);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zremrange, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    public boolean zremrangeByScore(String key, String namespace, double min,
                                    double max) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.zremrangeByScore(cacheKey(key, namespace), min, max);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zremrangeByScore, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Long zcard(String key, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.zcard(cacheKey(key, namespace));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zcard, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }


    public List<Object> batch(List<CacheOperate> operates) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            if (operates.isEmpty()) {
                return null;
            }
            jedis = getResource();
            Pipeline pip = jedis.pipelined();
            for (CacheOperate operate : operates) {
                switch (operate.getOperateType()) {
                    case GET:
                        pip.get(cacheKey(operate.getKey(), operate.getNamespace()));
                        break;
                    case PUT:
                        pip.set(cacheKey(operate.getKey(), operate.getNamespace()),
                                (String) operate.getValue());
                        if (operate.getExpire() != null) {
                            pip.expireAt(
                                    cacheKey(operate.getKey(), operate.getNamespace()),
                                    operate.getExpire().getTime() / 1000);
                        }
                        break;
                    case HGET:
                        pip.hget(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getField());
                        break;
                    case HGETALL:
                        pip.hgetAll(cacheKey(operate.getKey(),
                                operate.getNamespace()));
                        break;
                    case HPUT:
                        pip.hset(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getField(), (String) operate.getValue());
                        break;
                    case INCR:
                        pip.incrBy(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                (Long) operate.getValue());
                        break;
                    case DECR:
                        pip.decrBy(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                (Long) operate.getValue());
                        break;
                    case HINCR:
                        pip.hincrBy(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getField(), (Long) operate.getValue());
                        break;
                    case DELETE:
                        pip.del(cacheKey(operate.getKey(), operate.getNamespace()));
                        break;
                    case ZADD:
                        pip.zadd(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                Double.parseDouble(String.valueOf(operate.getValue())), operate.getField());
                        break;
                    case ZADDMAP:
                        Map<String, String> mapValue = operate.getKeyValuesMap();
                        Map<String, Double> map = new HashMap<String, Double>();
                        for (String key : mapValue.keySet()) {
                            map.put(key, Double.parseDouble(mapValue.get(key)));
                        }
                        pip.zadd(cacheKey(operate.getKey(), operate.getNamespace()), map);
                        break;
                    case ZCARD:
                        pip.zcard(cacheKey(operate.getKey(), operate.getNamespace()));
                        break;
                    case ZREM:
                        pip.zrem(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getField());
                        break;
                    case ZCOUNT:
                        pip.zcount(cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getMin(), operate.getMax());
                        break;
                    case ZRANGEBYSCORE:
                        if (operate.getCount() != 0) {
                            pip.zrangeByScore(cacheKey(operate.getKey(), operate.getNamespace()),
                                    operate.getMin(), operate.getMax(), operate.getOffset(), operate.getCount());
                        } else {
                            pip.zrangeByScore(cacheKey(operate.getKey(), operate.getNamespace()),
                                    operate.getMin(), operate.getMax());
                        }
                        break;
                    case ZREVRANGEBYSCORE:
                        if (operate.getCount() != 0) {
                            pip.zrevrangeByScore(cacheKey(operate.getKey(), operate.getNamespace()),
                                    operate.getMax(), operate.getMin(), operate.getOffset(), operate.getCount());
                        } else {
                            pip.zrevrangeByScore(cacheKey(operate.getKey(), operate.getNamespace()),
                                    operate.getMax(), operate.getMin());
                        }
                        break;
                    case EXPIRE:
                        pip.expire(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                (Integer) operate.getValue());
                        break;
                    case EXPIREAT:
                        pip.expireAt(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getExpire().getTime() / 1000);
                        break;
                    case HDEL:
                        pip.hdel(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getFields());
                        break;
                    case ZREMRANGE:
                        pip.zremrangeByRank(
                                cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getStart(), operate.getEnd());
                        break;
                    case ZREMRANGEBYSCORE:
                        pip.zremrangeByScore(cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getMin(), operate.getMax());
                        break;
                    case ZRANGE:
                        pip.zrange(cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getStart(), operate.getEnd());
                        break;
                    case MSET:
                        List<String> keysvalues = new ArrayList<String>();
                        for (Map.Entry<String, String> entry : operate.getKeyValuesMap().entrySet()) {
                            keysvalues.add(cacheKey(entry.getKey(), operate.getNamespace()));
                            keysvalues.add(entry.getValue());
                        }
                        pip.mset(keysvalues.toArray(new String[keysvalues.size()]));
                        break;
                    case LPUSH:
                        pip.lpush(cacheKey(operate.getKey(), operate.getNamespace()),
                                (String) operate.getValue());
                        break;
                    case LPOP:
                        pip.lpop(cacheKey(operate.getKey(), operate.getNamespace()));
                        break;
                    case RPUSH:
                        pip.rpush(cacheKey(operate.getKey(), operate.getNamespace()),
                                (String) operate.getValue());
                        break;
                    case RPOP:
                        pip.rpop(cacheKey(operate.getKey(), operate.getNamespace()));
                        break;
                    case LTRIM:
                        pip.ltrim(cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getStart(), operate.getEnd());
                        break;
                    case LINDEX:
                        pip.lindex(cacheKey(operate.getKey(), operate.getNamespace()),
                                (Long) operate.getValue());
                        break;
                    case LRANGE:
                        pip.lrange(cacheKey(operate.getKey(), operate.getNamespace()),
                                operate.getStart(), operate.getEnd());
                        break;
                    default:
                        break;
                }
                logger.info("缓存操作batch,单个操作类型:" + operate.getOperateType() + " key:" + cacheKey(operate.getKey(), operate.getNamespace()));
            }
            return pip.syncAndReturnAll();
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作batch耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    @Override
    public void refresh(String namespace) {

    }

    private boolean handleJedisException(JedisException jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            logger.error("Redis connection lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null)
                    && (jedisException.getMessage().indexOf("READONLY") != -1)) {
                logger.error("Redis connection are read-only slave.",
                        jedisException);
            } else {
                // dataException, isBroken=false
                logger.error("JedisDataException happen.", jedisException);
                return false;
            }
        } else {
            logger.error("Jedis exception happen.", jedisException);
        }
        return true;
    }

    private void closeResource(Jedis jedis, boolean conectionBroken) {
        try {
            if (conectionBroken) {
                // jedisPool.returnBrokenResource(jedis);
            } else {
                // jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            logger.error(
                    "return back jedis failed, will fore close the jedis.", e);
            jedis.close();
        }
    }

    private String cacheKey(String key, String namespace) {
        return namespace + "." + key;
    }

    /**
     * 设置key过期时间(秒数),重复设置将从当前时间点重新开始计时
     *
     * @param key
     * @param namespace
     * @param seconds   秒数
     * @return
     */
    public boolean expire(String key, String namespace, int seconds) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.expire(cacheKey(key, namespace), seconds);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作expire, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 设置key到某个时间点过期,重复设置将覆盖之前时间点
     *
     * @param key
     * @param namespace
     * @param untilDate 精确到秒
     * @return
     */
    public boolean expireAt(String key, String namespace, Date untilDate) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.expireAt(cacheKey(key, namespace), untilDate.getTime() / 1000);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作expireAt, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean add(String key, String namespace, Object value) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            // 只有当该key不存在时设置值
            return 1 == jedis.setnx(cacheKey(key, namespace), (String) value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作add, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean add(String key, String namespace, Object value, int expire) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            // 只有当该key不存在时设置值
            // String statusCode = jedis.set(cacheKey(key, namespace), (String) value, "NX", "PX", expire);
            // return "OK".equals(statusCode);
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作add, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    @Override
    public boolean delete(String key, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.del(cacheKey(key, namespace));
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作delete, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean delete(List<String> keys, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            List<String> removes = new ArrayList<String>();
            for (String key : keys) {
                removes.add(cacheKey(key, namespace));
                logger.info("缓存操作批量delete, key:" + cacheKey(key, namespace));
            }
            jedis.del(removes.toArray(new String[removes.size()]));
            return true;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作批量delete耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean mset(Map<String, String> keyValueMap, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            List<String> keysvalues = new ArrayList<String>();
            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                keysvalues.add(cacheKey(entry.getKey(), namespace));
                keysvalues.add(entry.getValue());
                logger.info("缓存操作mset, key:" + cacheKey(entry.getKey(), namespace));
            }
            return "OK".equals(jedis.mset(keysvalues.toArray(new String[keysvalues.size()])));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作mset耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public boolean msetnx(Map<String, String> keyValueMap, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            List<String> keysvalues = new ArrayList<String>();
            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                keysvalues.add(cacheKey(entry.getKey(), namespace));
                keysvalues.add(entry.getValue());
                logger.info("缓存操作msetnx, key:" + cacheKey(entry.getKey(), namespace));
            }
            return 1 == jedis.msetnx(keysvalues.toArray(new String[keysvalues.size()]));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作msetnx耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Map<String, String> mget(List<String> keys, String namespace) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        Map<String, String> result = new LinkedHashMap<String, String>();
        try {
            jedis = getResource();
            List<String> cacheKeys = new ArrayList<String>();
            for (String key : keys) {
                cacheKeys.add(cacheKey(key, namespace));
                logger.info("缓存操作mget, key:" + cacheKey(key, namespace));
            }
            List<String> values = jedis.mget(cacheKeys.toArray(new String[cacheKeys.size()]));
            for (int index = 0; index < keys.size(); index++) {
                result.put(keys.get(index), values.get(index));
            }
            return result;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return result;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作mget耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 将多个有序集合并为一个有续集并设置过期时间后返回目标集合及权重。<br>
     * 默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和。
     *
     * @param destination 目标集合
     * @param sets        多个有序集
     * @param namespace   命名空间
     * @param seconds     过期时间,以秒为单位
     * @param asc         true-正序返回集合,false-倒序返回集合
     * @return 目标有序集合及权重(Map.key - 成员, Map.value - 权重)
     */
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, Double> zunionstore(String destination, List<String> sets, String namespace, int seconds,
                                                     boolean asc) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Pipeline pipeline = jedis.pipelined();
            String[] cachesets = new String[sets.size()];
            for (int index = 0; index < sets.size(); index++) {
                cachesets[index] = cacheKey(sets.get(index), namespace);
            }
            pipeline.zunionstore(cacheKey(destination, namespace), cachesets);//合并至目标集合
            pipeline.expire(cacheKey(destination, namespace), seconds);//设置过期时间
            if (asc) {
                pipeline.zrangeWithScores(cacheKey(destination, namespace), 0, -1);//正序导出集合
            } else {
                pipeline.zrevrangeWithScores(cacheKey(destination, namespace), 0, -1);//倒序导出集合
            }
            List<Object> result = pipeline.syncAndReturnAll();
            LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<String, Double>();
            for (Tuple tuple : (Set<Tuple>) result.get(2)) {
                linkedHashMap.put(tuple.getElement(), tuple.getScore());
            }
            return linkedHashMap;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zunionstore, key:" + cacheKey(destination, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 按下标顺序返回目标有序集合
     *
     * @param key       目标集合
     * @param namespace 命名空间
     * @param start     下标起始位置
     * @param end       下标结束位置(-1表示全部)
     * @param asc       true-正序返回集合,false-倒序返回集合
     * @return 目标有序集合及权重(Map.key - 成员, Map.value - 权重)
     */
    public LinkedHashMap<String, Double> zrangeWithScores(String key, String namespace, long start, long end,
                                                          boolean asc) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<Tuple> tupleSet = null;
            if (asc) {
                tupleSet = jedis.zrangeWithScores(cacheKey(key, namespace), start, end);
            } else {
                tupleSet = jedis.zrevrangeWithScores(cacheKey(key, namespace), start, end);
            }
            LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<String, Double>();
            for (Tuple tuple : tupleSet) {
                linkedHashMap.put(tuple.getElement(), tuple.getScore());
            }
            return linkedHashMap;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrangeWithScores, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    @Override
    public void destroy() throws Exception {
        this.jedisPool.destroy();
        logger.info("==============销毁RedisCache连接池==============");
    }

    /**
     * 对有序集合中对指定成员进行增量功能，如果成员不存在则创建成员
     *
     * @param key
     * @param namespace
     * @param score
     * @param member
     * @return
     */
    public Double zincrby(String key, String namespace, double score, String member) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.zincrby(cacheKey(key, namespace), score, member);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zincrby, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 对有序集合中根据分数进行从高到底排序，并且下标区间范围内的成员及权重
     *
     * @param key
     * @param namespace
     * @param start
     * @param end
     * @return
     */
    public LinkedHashMap<String, Double> zrevrangeWithScores(String key, String namespace, long start, long end) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<Tuple> tuples = jedis.zrevrangeWithScores(cacheKey(key, namespace), start, end);
            LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
            for (Tuple tuple : tuples) {
                result.put(tuple.getElement(), tuple.getScore());
            }
            return result;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrevrangeWithScores, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }


    /**
     * 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)
     *
     * @param key
     * @param namespace
     * @param start
     * @param end
     * @return
     */
    public LinkedHashMap<String, Double> zrangeByScoreWithScores(String key, String namespace, long start, long end) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<Tuple> tuples = jedis.zrangeByScoreWithScores(cacheKey(key, namespace), start, end);
            LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
            for (Tuple tuple : tuples) {
                result.put(tuple.getElement(), tuple.getScore());
            }
            return result;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrangeByScoreWithScores, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 返回有序集合中指定成员的索引(正序)
     *
     * @param key
     * @param namespace
     * @param member
     * @return
     */
    public Long zrank(String key, String namespace, String member) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.zrank(cacheKey(key, namespace), member);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrank, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 判断member元素是否是集合key的成员
     *
     * @param key
     * @param namespace
     * @param member
     * @return Boolean
     */
    public boolean sismember(String key, String namespace, String member) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.sismember(cacheKey(key, namespace), member);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作sismember, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 用于计算有序集合中指定分数区间的成员数量。
     *
     * @param key
     * @param namespace
     * @param min
     * @param max
     * @return
     */
    public Long zcount(String key, String namespace, double min, double max) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.zcount(cacheKey(key, namespace), min, max);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zcount, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 返回有序集合中指定成员的索引(倒序)
     *
     * @param key
     * @param namespace
     * @param member
     * @return
     */
    public Long zrevrank(String key, String namespace, String member) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.zrevrank(cacheKey(key, namespace), member);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrevrank, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 将 key 所储存的值加上给定的增量值
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public Long incrBy(String key, String namespace, Long value) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.incrBy(cacheKey(key, namespace), value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作incrBy, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 将 key 所储存的值减去给定的减量值
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public Long decrBy(String key, String namespace, Long value) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.decrBy(cacheKey(key, namespace), value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作decrBy, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }


    /**
     * 将一个或多个值插入到列表头部。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * 返回 列表长度 ,当 key 存在但不是列表类型时，返回null。
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public Long lpush(String key, String namespace, String value) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.lpush(cacheKey(key, namespace), value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作lpush, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 命令用于移除并返回列表的第一个元素。
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public String lpop(String key, String namespace) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.lpop(cacheKey(key, namespace));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作lpop, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }


    /**
     * 命令用于将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 返回 列表长度 ,当列表存在但不是列表类型时，返回null。
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public Long rpush(String key, String namespace, String value) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.rpush(cacheKey(key, namespace), value);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作rpush, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public String rpop(String key, String namespace) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.rpop(cacheKey(key, namespace));
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作rpop, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
     * 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public List<String> lrange(String key, String namespace, long start, long end) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.lrange(cacheKey(key, namespace), start, end);

        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作lrange, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }


    /**
     * Redis Lindex 命令用于通过索引获取列表中的元素。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素，
     * -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param namespace
     * @param value
     * @return
     */
    public String lindex(String key, String namespace, long index) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.lindex(cacheKey(key, namespace), index);
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作lindex, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * Redis Ltrim 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * 下标 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，以 -1 表示列表的最后一个元素，
     * -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param namespace
     * @param start
     * @param end
     * @return
     */
    public boolean ltrim(String key, String namespace, long start, long end) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            String res = jedis.ltrim(cacheKey(key, namespace), start, end);
            if ("OK".equals(res)) {
                return true;
            } else {
                return false;
            }
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return false;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作ltrim, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * Redis Lrem 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * COUNT 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     *
     * @param key
     * @param namespace
     * @param count
     * @param value
     * @return
     */
    public Long Lrem(String key, String namespace, long count, String value) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Long res = jedis.lrem(cacheKey(key, namespace), count, value);
            return res;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return 0L;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作lrem, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * redisClient.zrangeByScore("myset", namespace, "(5", "10", 0, 3)
     *
     * @param key
     * @param namespace
     * @param min       前面有(表示开区间,没有(表示闭区间
     * @param max       前面有(表示开区间,没有(表示闭区间
     * @param offset    区间内开始数的位置
     * @param count     需要获取的数的数目,如果区间内的数小于所需要的数,就只返回区间内目前的数
     * @return
     */
    public Set<String> zrangeByScore(String key, String namespace, String min, String max, int offset, int count) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<String> res = jedis.zrangeByScore(cacheKey(key, namespace), min, max, offset, count);
            return res;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrangeByScore, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }

    /**
     * Redis zrevrangeByScore 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * redisClient.zrevrangeByScore("myset", namespace, "(5", "10", 0, 3)
     *
     * @param key
     * @param namespace
     * @param min       前面有(表示开区间,没有(表示闭区间
     * @param max       前面有(表示开区间,没有(表示闭区间
     * @param offset    区间内开始数的位置
     * @param count     需要获取的数的数目,如果区间内的数小于所需要的数,就只返回区间内目前的数
     * @return
     */
    public Set<String> zrevrangeByScore(String key, String namespace, String max, String min, int offset, int count) {
        long current = System.currentTimeMillis();
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<String> res = jedis.zrevrangeByScore(cacheKey(key, namespace), max, min, offset, count);
            return res;
        } catch (JedisException e) {
            broken = handleJedisException(e);
            return null;
        } finally {
            closeResource(jedis, broken);
            logger.info("缓存操作zrevrangeByScore, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        }
    }


    @Override
    public boolean putAndExpire(String key, String namespace, Object value,
                                Date expire) {
        long current = System.currentTimeMillis();
        List<CacheOperate> operates = new ArrayList<CacheOperate>();
        CacheOperate cacheOperate = new CacheOperate();
        cacheOperate.setOperateType(OperateType.PUT);
        cacheOperate.setKey(key);
        cacheOperate.setNamespace(namespace);
        cacheOperate.setValue(value);
        cacheOperate.setExpire(expire);
        operates.add(cacheOperate);
        logger.info("putAndExpire的入参为:" + JSON.toJSONString(operates));
        List<Object> res = batch(operates);
        logger.info("putAndExpire的结果为:" + res);
        // 第一个是 set ,第二个是 expireAt 操作
        // set 成功是返回  OK ,expireAt 成功是返回 1
        if (res != null && "OK".equals(res.get(0)) && 1L == (Long) res.get(1)) {
            logger.info("缓存操作putAndExpire成功, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
            return true;
        }
        logger.info("缓存操作putAndExpire失败, key:" + cacheKey(key, namespace) + ",耗时:" + (System.currentTimeMillis() - current) + "ms");
        return false;
    }
}
