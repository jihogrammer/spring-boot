package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class V3OrderService implements OrderCoordinator {

    private final AtomicInteger stock;

    private final MeterRegistry meterRegistry;

    V3OrderService(final MeterRegistry meterRegistry) {
        this.stock = new AtomicInteger(100);
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void order() {
        Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("my order count.")
                .register(this.meterRegistry)
                .record(() -> {
                    log.info("[ORDER]");
                    this.randomSleep(500);
                    this.stock.decrementAndGet();
                });
    }

    @Override
    public void cancel() {
        Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("my order cancel count.")
                .register(this.meterRegistry)
                .record(() -> {
                    log.info("[CANCEL]");
                    this.randomSleep(200);
                    this.stock.incrementAndGet();
                });
    }

    @Override
    public AtomicInteger getStock() {
        return this.stock;
    }

    private void randomSleep(final int baseMillis) {
        try {
            Thread.sleep(baseMillis + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
