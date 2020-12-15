package com.project.cache.factory.impl;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.project.cache.client.MemCache;
import com.project.cache.dto.CacheConfig;
import com.project.cache.factory.MemCacheFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;


import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.buffer.SimpleBufferAllocator;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean;

@Component
@SuppressWarnings("deprecation")
public class MemCacheFactoryImpl implements MemCacheFactory {

    @Resource
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public MemCache createMemCache(CacheConfig cacheConfig) throws Exception {
        XMemcachedClientFactoryBean factoryBean = new XMemcachedClientFactoryBean();

        factoryBean.setServers(cacheConfig.getConnectAddress().trim());
        factoryBean.setFailureMode(true);
        factoryBean.setConnectTimeout(2000);
        factoryBean.setConnectionPoolSize(cacheConfig.getMaxActive());
        factoryBean.setCommandFactory(new BinaryCommandFactory());
        factoryBean.setSessionLocator(new KetamaMemcachedSessionLocator());
        factoryBean.setTranscoder(new SerializingTranscoder());
        factoryBean.setBufferAllocator(new SimpleBufferAllocator());

        //支持用户密码模式
        if(StringUtils.isNotEmpty(cacheConfig.getCacheUser()) && StringUtils.isNotEmpty(cacheConfig.getPassword())) {
            Map<InetSocketAddress, AuthInfo> authInfoMap = new HashMap<InetSocketAddress, AuthInfo>();
            List<InetSocketAddress> addrList = AddrUtil.getAddresses(cacheConfig.getConnectAddress().trim());
            if(addrList != null && addrList.size() >= 1) {
                AuthInfo authInfo = AuthInfo.plain(cacheConfig.getCacheUser(), cacheConfig.getPassword());
                authInfoMap.put(addrList.get(0), authInfo);
            }
            factoryBean.setAuthInfoMap(authInfoMap);
        }
        MemcachedClient memcachedClient = (MemcachedClient) factoryBean.getObject();
        return new MemCache(memcachedClient);
    }

}
