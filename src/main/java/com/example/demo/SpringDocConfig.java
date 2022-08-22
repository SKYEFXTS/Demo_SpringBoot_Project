package com.example.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// OpenAPI json //
// http://localhost:8080/v3/api-docs

// Swagger UI //
// http://localhost:8080/swagger-ui/index.html#/


@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi studentApi(){
        return GroupedOpenApi
                .builder()
                .group("Student Registry Public API")
                .packagesToScan("com.example.demo.student")
                .pathsToMatch("/api/student/**")
                .build();
    }

    @Bean
    public OpenAPI studentOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Student registry API demo project")
                        .description("Demo API for Swagger configuration and testing")
                        .version("v2.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Oshan Nanayakkara")
                                .email("oshan.nanayakkara@pearson.com")
                                .url("Demo url"))
                );
    }

}

