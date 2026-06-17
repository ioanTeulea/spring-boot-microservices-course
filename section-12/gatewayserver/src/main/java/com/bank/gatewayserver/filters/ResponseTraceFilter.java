package com.bank.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(2)
@Component
public class ResponseTraceFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    private final FilterUtility filterUtility;

    public ResponseTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            String correlationId = filterUtility.getCorrelationId(requestHeaders);
            if (correlationId != null && !exchange.getResponse().getHeaders().containsKey(FilterUtility.CORRELATION_ID)) {
                logger.debug("Correlation id found in the response headers: {}. ", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
            } else {
                logger.debug("No correlation id found in the response headers.");
            }
        }));

    }
}
