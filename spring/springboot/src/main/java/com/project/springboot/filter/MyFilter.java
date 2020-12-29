package com.project.springboot.filter;

import com.sun.tools.corba.se.idl.constExpr.Not;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 过滤器
 * @order 影响bean加载的顺序
 */
@Component
@Order(2)
public class MyFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 拿到当前的Http请求
        ServerHttpRequest request = exchange.getRequest();
        //ServerHttpResponse response =  exchange.getResponse();
        String token = request.getHeaders().getFirst("token");
        if (null == token) {
            URI uri = null;
            try {
                uri = new URI("http://www.baidu.com/");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            ServerHttpRequest authErrorReq = request.mutate().uri(uri).build();
            //erverWebExchange.mutate类似，构建一个新的ServerWebExchange
            ServerWebExchange authErrorExchange = exchange.mutate().request(authErrorReq).build();
            return chain.filter(authErrorExchange);
        } else {
            return chain.filter(exchange);
        }
    }
}
