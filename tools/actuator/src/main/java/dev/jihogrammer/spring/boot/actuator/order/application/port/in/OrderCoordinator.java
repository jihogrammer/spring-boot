package dev.jihogrammer.spring.boot.actuator.order.application.port.in;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderCoordinator {

    void order();

    void cancel();

    AtomicInteger getStock();

}
