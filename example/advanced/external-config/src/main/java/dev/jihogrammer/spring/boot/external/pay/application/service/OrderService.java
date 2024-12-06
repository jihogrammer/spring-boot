package dev.jihogrammer.spring.boot.external.pay.application.service;

import dev.jihogrammer.spring.boot.external.pay.application.port.in.Orders;
import dev.jihogrammer.spring.boot.external.pay.application.port.out.Payments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderService implements Orders {

    private final Payments payments;

    @Override
    public void order(int money) {
        //
        // Some complex order logics...
        //
        this.payments.pay(money);
    }

}
