package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    GaugeMetric gaugeMetric(final OrderCoordinator orderCoordinator, final MeterRegistry registry) {
        return new GaugeMetric(orderCoordinator, registry);
    }

    @Slf4j
    @RequiredArgsConstructor
    static class GaugeMetric {

        private final OrderCoordinator orderCoordinator;

        private final MeterRegistry registry;

        @PostConstruct
        void init() {
            Gauge.builder("my.stock", this.orderCoordinator, (service) -> {
                log.info("[STOCK]");
                return service.getStock().get();
            }).register(this.registry);
        }

    }

}
