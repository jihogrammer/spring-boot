package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class OrderServiceConfig {

    @Bean
    OrderCoordinator orderCoordinator() {
        return new V4OrderService();
    }

    @Bean
    TimedAspect timedAspect(final MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    MeterBinder gaugeMetric(final OrderCoordinator orderCoordinator) {
        return registry -> Gauge.builder("my.stock", orderCoordinator, (service) -> {
            log.info("[STOCK]");
            return service.getStock().get();
        }).register(registry);
    }

}
