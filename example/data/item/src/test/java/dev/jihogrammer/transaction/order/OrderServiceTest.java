package dev.jihogrammer.transaction.order;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    @Autowired OrderService service;

    @Autowired OrderRepository repository;

    @Test
    void complete() throws NotEnoughAccountException {
        // given
        var order = new Order();
        order.setUsername(OrderService.NORMAL_USERNAME);

        // when
        var ordered = this.service.order(order);
        var saved = this.repository.findById(ordered.getId()).orElseThrow();

        // then
        assertThat(saved).isEqualTo(ordered);
        assertThat(saved.getStatus()).isEqualTo(OrderService.SUCCEED_STATUS);
    }

    @Test
    void systemError() {
        // given
        var order = new Order();
        order.setUsername(OrderService.SYSTEM_EXCEPTION_USERNAME);

        // when
        assertThatThrownBy(() -> this.service.order(order)).isInstanceOf(RuntimeException.class);

        // then
        assertThat(this.repository.findById(order.getId())).isEmpty();
    }

    @Test
    void businessError() {
        // given
        var order = new Order();
        order.setUsername(OrderService.BUSINESS_EXCEPTION_USERNAME);

        // when
        try {
            this.service.order(order);
            fail("Not expected suite.");
        } catch (NotEnoughAccountException e) {
            log.warn("Do something for business exception status.");
            log.error(e.getMessage(), e);
        }
        var saved = this.repository.findById(order.getId()).orElseThrow();
        log.info("saved order={}", order);

        // then
        assertThat(saved.getStatus()).isEqualTo(OrderService.PENDING_STATUS);
    }

}
