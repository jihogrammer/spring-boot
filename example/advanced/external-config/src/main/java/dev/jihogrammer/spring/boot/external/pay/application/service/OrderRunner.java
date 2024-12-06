package dev.jihogrammer.spring.boot.external.pay.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderRunner implements ApplicationRunner {

    private final OrderService orderService;

    @Override
    public void run(ApplicationArguments args) {
        this.orderService.order((int) (Math.random() * 10000));
    }

}
