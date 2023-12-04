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
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(r -> r.path("/api/v1/customers/**")
                        .uri("lb://CUSTOMERS-SERVICE"))
                .route(r -> r.path("/api/v1/orders/**")
                        .uri("lb://ORDER-SERVICE")).build();
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

