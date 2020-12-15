package com.project.cache.client;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import org.apache.log4j.Logger;

import java.util.Date;

public class MemCache implements BaseCache {
    private final Logger logger = Logger.getLogger(getClass());

    // 缓存客户端
    private MemcachedClient memcachedClient;

    // 版本
    private Long version;

    private static final String CONTEXT_NAMESPACE = "context.";

    private static final String VERSION_KEY = ".version";

    public MemCache() {
    }

    public MemCache(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }


    public Object get(String key, String namespace) {
        long start = System.currentTimeMillis();
        logger.info("memcached get Key:" + cacheKey(key, namespace));
        try {
            return memcachedClient.get(cacheKey(key, namespace));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            logger.info("memcached get Key:" + cacheKey(key, namespace)
                    + " 耗时：" + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public Object get(String key, String namespace, int time) {
        long start = System.currentTimeMillis();
        logger.info("memcached get Key:" + cacheKey(key, namespace));
        try {
            return memcachedClient.getAndTouch(cacheKey(key, namespace), time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            logger.info("memcached get Key:" + cacheKey(key, namespace)
                    + ", time:" + time + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public boolean put(String key, String namespace, Object value) {
        long start = System.currentTimeMillis();
        logger.info("memcached set Key:" + cacheKey(key, namespace)
                + ", value:" + value);
        try {
            return memcachedClient.set(cacheKey(key, namespace), 0, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached set Key:" + cacheKey(key, namespace)
                    + ", value:" + value + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }

    }

    public boolean put(String key, String namespace, Object value, int time) {
        long start = System.currentTimeMillis();
        logger.info("memcached set Key:" + cacheKey(key, namespace)
                + ", value:" + value + ", time:" + time);
        try {
            return memcachedClient.set(cacheKey(key, namespace), time, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached set Key:" + cacheKey(key, namespace)
                    + ", value:" + value + ", time:" + time + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public boolean put(String key, String namespace, String value, int time, long cas) {
        long start = System.currentTimeMillis();
        logger.info("memcached cas Key:" + cacheKey(key, namespace)
                + ", value:" + value + ", time:" + time);
        try {
            return memcachedClient.cas(cacheKey(key, namespace), time, value, cas);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached cas Key:" + cacheKey(key, namespace)
                    + ", value:" + value + ", time:" + time + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public long incr(String key, String namespace, long interval, long start) {
        long startTime = System.currentTimeMillis();
        logger.info("memcached incr Key:" + cacheKey(key, namespace)
                + ", interval:" + interval + ", start:" + start);
        try {
            return memcachedClient.incr(cacheKey(key, namespace), interval,
                    start);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            logger.info("memcached incr Key:" + cacheKey(key, namespace)
                    + ", interval:" + interval + ", start:" + start + " 耗时："
                    + ((System.currentTimeMillis() - startTime) / 1000f) + "秒");
        }
    }

    public long decr(String key, String namespace, long interval) {
        long start = System.currentTimeMillis();
        logger.info("memcached decr Key:" + cacheKey(key, namespace)
                + ", interval:" + interval);
        try {
            return memcachedClient.decr(cacheKey(key, namespace), interval);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            logger.info("memcached decr Key:" + cacheKey(key, namespace)
                    + ", interval:" + interval + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public GetsResponse<Integer> gets(String key, String namespace) {
        long start = System.currentTimeMillis();
        logger.info("memcached gets Key:" + cacheKey(key, namespace)
                + ", namespace:" + namespace);
        try {
            return memcachedClient.gets(cacheKey(key, namespace));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            logger.info("memcached gets Key:" + cacheKey(key, namespace)
                    + ", namespace:" + namespace + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public boolean add(String key, String namespace, Object value) {
        long start = System.currentTimeMillis();
        logger.info("memcached add Key:" + cacheKey(key, namespace)
                + ", value:" + value);
        try {
            return memcachedClient.add(cacheKey(key, namespace), 0, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached add Key:" + cacheKey(key, namespace)
                    + ", value:" + value + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    public boolean add(String key, String namespace, Object value, int time) {
        long start = System.currentTimeMillis();
        logger.info("memcached add Key:" + cacheKey(key, namespace)
                + ", value:" + value);
        try {
            return memcachedClient.add(cacheKey(key, namespace), time, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached add Key:" + cacheKey(key, namespace)
                    + ", value:" + value + ", time:" + time + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }
    }

    @Override
    public boolean delete(String key, String namespace) {
        long start = System.currentTimeMillis();
        logger.info("memcached delete Key:" + cacheKey(key, namespace));
        try {
            return memcachedClient.delete(cacheKey(key, namespace));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached delete Key:" + cacheKey(key, namespace)
                    + " 耗时：" + ((System.currentTimeMillis() - start) / 1000f)
                    + "秒");
        }
    }

    public void refresh(String namespace) {
        logger.info("memcached refresh Version");
        version = null;
    }

    public void updateVersion(String namespace) {
        logger.info("memcached update Version:" + versionKey(namespace));
        try {
            Long currentVersion = (Long) memcachedClient
                    .get(versionKey(namespace));
            memcachedClient.set(versionKey(namespace), 0, currentVersion + 1l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String cacheKey(String key, String namespace) {
        return namespace + "." + getVersion(namespace) + "." + key;
    }

    private String versionKey(String namespace) {
        return CONTEXT_NAMESPACE + namespace + VERSION_KEY;
    }

    private Long getVersion(String namespace) {
        try {
            if (version == null) {
                version = (Long) memcachedClient.get(versionKey(namespace));
            }
            if (version == null) {
                version = 1L;
                memcachedClient.set(versionKey(namespace), 0, version);
            }
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }


    @Override
    public boolean putAndExpire(String key, String namespace, Object value,
                                Date expire) {
        long start = System.currentTimeMillis();
        int time = calLastedTime(expire);
        logger.info("memcached putAndExpire Key:" + cacheKey(key, namespace)
                + ", value:" + value + ", time:" + time);
        try {
            return memcachedClient.set(cacheKey(key, namespace), time, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            logger.info("memcached putAndExpire Key:" + cacheKey(key, namespace)
                    + ", value:" + value + ", time:" + time + " 耗时："
                    + ((System.currentTimeMillis() - start) / 1000f) + "秒");
        }

    }

    public int calLastedTime(Date expire) {
        long a = new Date().getTime();
        long b = expire.getTime();
        return (int) ((b - a) / 1000);
    }


    @Override
    public void destroy() throws Exception {
        memcachedClient.shutdown();
        logger.info("==============销毁MemCache连接池==============");
    }
}