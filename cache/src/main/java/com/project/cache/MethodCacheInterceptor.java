package com.project.cache;

import com.project.cache.patten.BaseCachePattern;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 缓存方法拦截器
 *
 * @author zhangzh01
 *
 */
public class MethodCacheInterceptor implements MethodInterceptor,
        InitializingBean {

    private static final Logger logger = Logger
            .getLogger(MethodCacheInterceptor.class);

    private BaseCachePattern cachePattern;

    private String cacheNamespace;

    public void setCachePattern(BaseCachePattern cachePattern) {
        this.cachePattern = cachePattern;
    }

    public void setCacheNamespace(String cacheNamespace) {
        this.cacheNamespace = cacheNamespace;
    }

    /**
     * 缓存方法拦截器核心代码
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object result;

        String cacheKey = getCacheKey(targetName, methodName, arguments);
        synchronized (this) {
            result = cachePattern.get(cacheKey, cacheNamespace);
            if (result == null) {
                logger.info(cacheKey + "存入缓存");
                // 调用实际的方法
                result = invocation.proceed();
                cachePattern.put(cacheKey, cacheNamespace, result);
            } else {
                logger.info(cacheKey + "使用缓存加载");
            }
        }
        return result;
    }

    public void afterPropertiesSet() throws Exception {
        // logger.info(cache +
        // " A cache is required. Use setCache(Cache) to provide one.");
    }

    /**
     * 缓存命名规范
     *
     * @param targetName
     * @param methodName
     * @param arguments
     * @return String
     */
    private String getCacheKey(String targetName, String methodName,
                               Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }

}
