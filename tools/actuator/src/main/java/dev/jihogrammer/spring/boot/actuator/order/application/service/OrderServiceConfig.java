package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderServiceConfig {

    @Bean
    OrderCoordinator orderCoordinator() {
        return new V2OrderService();
    }

    @Bean
    CountedAspect countedAspect(final MeterRegistry registry) {
        return new CountedAspect(registry);
    }

}
