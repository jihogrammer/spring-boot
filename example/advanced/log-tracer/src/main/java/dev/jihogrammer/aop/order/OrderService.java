package dev.jihogrammer.aop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String orderItem(final String itemId) {
        var result = this.orderRepository.save(itemId);
        log.info("orderItem result={};", result);
        return result;
    }

}
