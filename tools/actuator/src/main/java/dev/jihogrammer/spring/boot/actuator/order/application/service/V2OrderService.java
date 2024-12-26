package dev.jihogrammer.spring.boot.actuator.order.application.service;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class V2OrderService implements OrderCoordinator {

    private final AtomicInteger stock;

    V2OrderService() {
        this.stock = new AtomicInteger(100);
    }

    @Counted("my.order")
    @Override
    public void order() {
        log.info("[ORDER]");
        this.stock.decrementAndGet();
    }

    @Counted("my.order")
    @Override
    public void cancel() {
        log.info("[CANCEL]");
        this.stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return this.stock;
    }

}
