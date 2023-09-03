package com.example.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
       return ((exchange, chain) -> {
           ServerHttpRequest req = exchange.getRequest();
           ServerHttpResponse res = exchange.getResponse();
           log.info("CustomPreFilter req id ->{}",req.getId());

           return chain.filter(exchange)
                   .then(Mono.fromRunnable(()->{
                       log.info("Custom Post Filter ->{}",res.getStatusCode());
           }));
       });
    }

    public static class Config{
        // Put the configuration properties
    }
}
