package dev.jihogrammer.gateway.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "${gateway.service}")
public class GatewayServiceConfig {
}
