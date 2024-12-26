package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed("my.order")
@Slf4j
class V4OrderService implements OrderCoordinator {

    private final AtomicInteger stock;

    V4OrderService() {
        this.stock = new AtomicInteger(100);
    }

    @Override
    public void order() {
        log.info("[ORDER]");
        this.randomSleep(500);
        this.stock.decrementAndGet();
    }

    @Override
    public void cancel() {
        log.info("[CANCEL]");
        this.randomSleep(200);
        this.stock.incrementAndGet();
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
