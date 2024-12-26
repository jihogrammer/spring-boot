package dev.jihogrammer.spring.boot.actuator.order.adaptor.web;

import dev.jihogrammer.spring.boot.actuator.order.application.port.in.OrderCoordinator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
class OrderController {

    private final OrderCoordinator orderCoordinator;

    @GetMapping
    Map<String, Object> order() {
        log.info("{}::order", this.getClass().getSimpleName());
        this.orderCoordinator.order();
        return Map.of("status", "OK");
    }

    @GetMapping("/cancel")
    Map<String, Object> cancel() {
        log.info("{}::cancel", this.getClass().getSimpleName());
        this.orderCoordinator.cancel();
        return Map.of("status", "OK");
    }

    @GetMapping("/stock")
    Map<String, Object> stock() {
        log.info("{}::stock", this.getClass().getSimpleName());
        var result = this.orderCoordinator.getStock();
        return Map.of("status", "OK", "result", result);
    }

}
