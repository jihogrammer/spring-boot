package dev.jihogrammer.spring.boot.external.pay.adaptor;

import dev.jihogrammer.spring.boot.external.pay.application.port.out.Payments;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MockPaymentsAdaptor implements Payments {

    @Override
    public void pay(int money) {
        log.info("mocked pay: {}", money);
    }

}
