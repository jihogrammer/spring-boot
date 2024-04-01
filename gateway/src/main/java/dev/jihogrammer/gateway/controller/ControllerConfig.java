package dev.jihogrammer.gateway.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Bean
    public GetMappingEndPointFinder controllerFinder(final ApplicationContext context) {
        return new GetMappingEndPointFinder(context);
    }

}
