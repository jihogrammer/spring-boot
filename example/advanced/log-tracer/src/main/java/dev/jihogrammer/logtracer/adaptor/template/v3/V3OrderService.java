package dev.jihogrammer.logtracer.adaptor.template.v3;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class V3OrderService implements OrderPort {

    private final Orders orders;

    private final Tracer tracer;

    @Override
    public void orderItem(final ItemId itemId) {
        final var status = this.tracer.start("OrderPort.orderItem(" + itemId + ")");

        try {
            this.orders.save(itemId);

            this.tracer.end(status);
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
