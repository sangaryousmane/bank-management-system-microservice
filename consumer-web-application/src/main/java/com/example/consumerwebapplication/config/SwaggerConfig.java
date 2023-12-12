package com.example.consumerwebapplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "OUSMANE SANGARY",
                        email = "sangary7683@yahoo.com",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"),
                description = "OPEN API DOCUMENTATION FOR THE CONSUMER MICROSERVICE",
                title = "OPEN API SPECIFICATION FOR CONSUMING CUSTOMERS API",
                version = "1.0",
                license = @License(
                        name = "MIT LICENSE",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"),
                termsOfService = "FREE TO USE",
                summary = "RESTFUL ENDPOINTS FOR CONSUMER SERVICE"),
        servers = {
                @Server(
                        description = "LOCAL ENVIRONMENT",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"
                ),
                @Server(
                        description = "PRODUCTION ENVIRONMENT",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java")
        }
)
public class SwaggerConfig {
}
