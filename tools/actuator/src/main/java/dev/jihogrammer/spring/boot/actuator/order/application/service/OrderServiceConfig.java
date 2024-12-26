package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderServiceConfig {

    @Bean
    OrderCoordinator orderCoordinator(final MeterRegistry meterRegistry) {
        return new V3OrderService(meterRegistry);
    }

}
