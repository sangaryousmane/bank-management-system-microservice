package org.atalibdev.getway;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;


@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Bean
    public KeyResolver keyResolver(){
        return resolver -> Mono.just("userKey");
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> factory() {
        return fact -> fact.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .build());
    }

    @Bean
    public RouteLocator router(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/api/v1/accounts/**")
                        .filters(f -> f.circuitBreaker(c -> {
                            c.setName("ACCOUNT-SERVICE");
                            c.setFallbackUri("forward:/accountServiceFallBack");
                        }))
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.circuitBreaker(c -> {
                            c.setName("CUSTOMER-SERVICE");
                            c.setFallbackUri("forward:/customerServiceFallBack");
                        })).uri("lb://CUSTOMER-SERVICE"))
                .route(r -> r.path("/api/v1/consumerApp/**")
                        .filters(f -> f.circuitBreaker(c -> {
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

