package com.example.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
       return ((exchange, chain) -> {
           ServerHttpRequest req = exchange.getRequest();
           ServerHttpResponse res = exchange.getResponse();
           log.info("GlobalFilter baseMessage ->{}",config.getBaseMessage());
           if(config.isPreLogger()){
               log.info("GlobalFilter Pre req id ->{}",req.getId());
           }


           return chain.filter(exchange)
                   .then(Mono.fromRunnable(()->{
                       if (config.isPostLogger()) {
                           log.info("GlobalFilter Post ->{}",res.getStatusCode());
                       }

           }));
       });
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
