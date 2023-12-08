package org.atalibdev.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator router(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/accounts/**")
                        .filters(f -> f.circuitBreaker(c-> {
                            c.setName("ACCOUNT-SERVICE");
                            c.setFallbackUri("forward:/accountServiceFallBack");
                        })).uri("lb://ACCOUNT-SERVICE"))
                .route(r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.circuitBreaker(c -> {
                           c.setName("CUSTOMER-SERVICE");
                           c.setFallbackUri("forward:/accountServiceFallBack");
                        })).uri("lb://CUSTOMER-SERVICE"))
                .route(r -> r.path("/api/v1/consumerApp/**")
                        .filters(f -> f.circuitBreaker(c ->{
                            c.setName("WEB-APPLICATION-CONSUMER");
                            c.setFallbackUri("forward:/webServiceFallBack");
                        })).uri("lb://WEB-APPLICATION-CONSUMER"))
                .build();
    }

    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoute(
            ReactiveDiscoveryClient reactiveDiscoveryClient,
            DiscoveryLocatorProperties discoveryLocatorProperties
    ) {
        return new DiscoveryClientRouteDefinitionLocator(
                reactiveDiscoveryClient,
                discoveryLocatorProperties
        );
    }
}

