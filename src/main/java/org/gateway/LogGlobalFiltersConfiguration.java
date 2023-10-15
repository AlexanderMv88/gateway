package org.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class LogGlobalFiltersConfiguration {

    final Logger log = LoggerFactory.getLogger(LogGlobalFiltersConfiguration.class);

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("postGlobalFilter from LogGlobalFiltersConfiguration executed");
            }));
        };
    }
}
