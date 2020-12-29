package com.project.springboot.webflux.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Map;


/**
 * 过滤器
 * 由于这里使用了webflux框架，跟平时的mvc是有一些区别的。
 * @order 影响bean加载的顺序
 */
@Component
@Order(2)
@Slf4j
public class MyFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("过滤请求中...");
        // session
        Mono<WebSession> sessionMono = exchange.getSession();
        // 设置的attributes
        Map<String, Object> attributes = exchange.getAttributes();
        // request请求
        ServerHttpRequest request = exchange.getRequest();
        // 跳转
        // request = request.mutate().path("/ex/ex").build();
        //ServerWebExchange.mutate类似，构建一个新的ServerWebExchange
        ServerWebExchange authErrorExchange = exchange.mutate().request(request).build();

        log.info("过滤请求结束...");
        return chain.filter(authErrorExchange);

    }
}
