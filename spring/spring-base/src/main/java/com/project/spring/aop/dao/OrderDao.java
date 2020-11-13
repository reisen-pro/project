package com.project.spring.aop.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * java.lang.VerifyError: Stack map does not match the one at exception handler 9
 * cglib代理的时候会报错。
 * 网上找的解决方案是配置vm参数 -noverify 禁止字节码校验
 */
@Slf4j
@Component
public class OrderDao {
    public void save() {
        log.info("新订单入库,订单号:{},下单时间:{}", UUID.randomUUID(), LocalDateTime.now());
        //throw new NullPointerException();
    }
}
