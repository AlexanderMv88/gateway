package org.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MyLogGatewayFilterFactory extends AbstractGatewayFilterFactory<MyLogGatewayFilterFactory.Config> {

    final Logger log = LoggerFactory.getLogger(MyLogGatewayFilterFactory.class);

    public MyLogGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (config.isPreLogger()) {
                log.info("LogGatewayFilterFactory preLogger executed. Msg: "
                        + config.getMsg());
            }
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Post-processing
                        if (config.isPostLogger()) {
                            log.info("LogGatewayFilterFactory postLogger executed. Msg: "
                                    + config.getMsg());
                        }
                    }));
        });
    }

    public static class Config {
        private String msg;
        private boolean preLogger;
        private boolean postLogger;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isPreLogger() {
            return preLogger;
        }

        public void setPreLogger(boolean preLogger) {
            this.preLogger = preLogger;
        }

        public boolean isPostLogger() {
            return postLogger;
        }

        public void setPostLogger(boolean postLogger) {
            this.postLogger = postLogger;
        }

        public Config() {
        }

        public Config(String msg, boolean preLogger, boolean postLogger) {
            this.msg = msg;
            this.preLogger = preLogger;
            this.postLogger = postLogger;
        }
    }
}
