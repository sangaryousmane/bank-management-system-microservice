package com.example.springserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // Our eureka server
public class SpringServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServiceRegistryApplication.class, args);
    }

}
