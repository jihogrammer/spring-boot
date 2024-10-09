package dev.jihogrammer.logtracer.adaptor.template.v4;

import dev.jihogrammer.logtracer.application.port.in.AbstractTemplate;
import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class V4OrderService implements OrderPort {

    private final Orders orders;

    private final Tracer tracer;

    @Override
    public void orderItem(final ItemId itemId) {
        var template = new AbstractTemplate<Void>(tracer) {
            @Override
            protected Void call() {
                orders.save(itemId);
                return null;
            }
        };
        template.execute("OrderPort.orderItem(" + itemId + ")");
    }

}
