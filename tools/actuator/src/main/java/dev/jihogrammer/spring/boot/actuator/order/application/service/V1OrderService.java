package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class V1OrderService implements OrderCoordinator {

    private final AtomicInteger stock;

    private final MeterRegistry meterRegistry;

    V1OrderService(final MeterRegistry meterRegistry) {
        this.stock = new AtomicInteger(100);
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void order() {
        log.info("[ORDER]");
        this.stock.decrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("my order count.")
                .register(this.meterRegistry)
                .increment();
    }

    @Override
    public void cancel() {
        log.info("[CANCEL]");
        this.stock.incrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("my order cancel count.")
                .register(this.meterRegistry)
                .increment();
    }

    @Override
    public AtomicInteger getStock() {
        return this.stock;
    }

}
