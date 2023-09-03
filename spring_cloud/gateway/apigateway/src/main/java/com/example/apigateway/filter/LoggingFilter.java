package com.example.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter filter = new OrderedGatewayFilter(((exchange, chain) -> {
           ServerHttpRequest req = exchange.getRequest();
           ServerHttpResponse res = exchange.getResponse();
           log.info("LoggingFilter baseMessage ->{}",config.getBaseMessage());
           if(config.isPreLogger()){
               log.info("LoggingFilter Pre req id ->{}",req.getId());
           }
           return chain.filter(exchange)
                   .then(Mono.fromRunnable(()->{
                       if (config.isPostLogger()) {
                           log.info("LoggingFilter Post ->{}",res.getStatusCode());
                       }

           }));
        }), Ordered.HIGHEST_PRECEDENCE);//우선순위 적용 가능
        return filter;

    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
