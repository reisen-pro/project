package com.project.cache.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.cache.client.BaseCache;
import com.project.cache.patten.BaseCachePattern;
import com.project.util.spring.SpringContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;


public class CacheRefreshServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String namespace = req.getParameter("namespace");
        // ehCacheClient
        // memCacheClient
        String client = req.getParameter("client");
        // ebpCachePattern
        String pattern = req.getParameter("pattern");
        logger.info("Cache刷新开始");
        if (StringUtils.hasText(namespace) && StringUtils.hasText(client)) {
            logger.info("Cache刷新:" + namespace + "." + client);
            BaseCache cache = (BaseCache) SpringContext.getBean(client);
            cache.refresh(namespace);
        } else if (StringUtils.hasText(namespace) && StringUtils.hasText(pattern)) {
            logger.info("Cache刷新:" + namespace + "." + pattern);
            BaseCachePattern cachePattern = (BaseCachePattern) SpringContext.getBean(pattern);
            cachePattern.refresh(namespace);
        }
        logger.info("Cache刷新结束");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}

