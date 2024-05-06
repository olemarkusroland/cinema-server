package com.booleanuk.api.cinema;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("cinema-api")
                .packagesToScan("com.booleanuk.api.cinema.endpoints")
                .build();
    }
}
