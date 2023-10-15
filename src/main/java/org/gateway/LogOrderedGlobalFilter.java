package org.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class LogOrderedGlobalFilter implements GlobalFilter, Ordered {

    final Logger log = LoggerFactory.getLogger(LogOrderedGlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("first LogOrderedGlobalFilter executed");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("last LogOrderedGlobalFilter executed");
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
