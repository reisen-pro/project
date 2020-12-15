package com.project.cache.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.cache.client.MemCache;
import com.project.util.spring.SpringContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemCacheVersionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String namespace = req.getParameter("namespace");
        logger.info("更新Memcached:" + namespace + "版本开始");
        MemCache cache = (MemCache) SpringContext.getBean("memCacheClient");
        cache.updateVersion(namespace);
        logger.info("更新Memcached:" + namespace + "版本结束");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
