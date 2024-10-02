package dev.jihogrammer.logtracer.adaptor.app.v1_bean_definition;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;

class V1OrderService implements OrderPort {

    private final Orders orders;

    private final TraceTemplate traceTemplate;

    public V1OrderService(final Orders orders, final Tracer tracer) {
        this.orders = orders;
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public void orderItem(final ItemId itemId) {
        this.traceTemplate.execute("OrderPort.orderItem(" + itemId + ")", () -> {
            orders.save(itemId);
            return null;
        });
    }

}
