package dev.jihogrammer.logtracer.adaptor.template.v0;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class V0OrderService implements OrderPort {

    private final Orders orders;

    @Override
    public void orderItem(final ItemId itemId) {
        this.orders.save(itemId);
    }

}
