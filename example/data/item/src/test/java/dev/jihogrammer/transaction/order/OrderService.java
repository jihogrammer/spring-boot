package dev.jihogrammer.transaction.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class OrderService {

    static final String NORMAL_USERNAME = "Normal";

    static final String SYSTEM_EXCEPTION_USERNAME = "RuntimeException";

    static final String BUSINESS_EXCEPTION_USERNAME = "NotEnoughMoneyException";

    static final String SUCCEED_STATUS = "SUCCEED";

    static final String PENDING_STATUS = "PENDING";

    private final OrderRepository repository;

    @Transactional
    public Order order(final Order order) throws NotEnoughAccountException {
        log.info("order={}", order);
        final var savedOrder = this.repository.save(order);

        log.info("Start to order process.");
        if ("RuntimeException".equalsIgnoreCase(order.getUsername())) {
            throw new RuntimeException("Occurred a System Exception");
        }
        if ("NotEnoughMoneyException".equalsIgnoreCase(order.getUsername())) {
            savedOrder.setStatus(PENDING_STATUS);
            throw new NotEnoughAccountException("Occurred a Business Exception");
        }

        savedOrder.setStatus(SUCCEED_STATUS);

        log.info("Completed to order process. order={}", savedOrder);
        return savedOrder;
    }

}
