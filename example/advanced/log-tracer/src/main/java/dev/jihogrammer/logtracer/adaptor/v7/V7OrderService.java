package dev.jihogrammer.logtracer.adaptor.v7;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V7OrderService {

    private final V7OrderAdaptor orders;

    private final TraceTemplate traceTemplate;

    public V7OrderService(final V7OrderAdaptor orders, final Tracer tracer) {
        this.orders = orders;
        this.traceTemplate = new TraceTemplate(tracer);
    }

    public void orderItem(final ItemId itemId) {
        this.traceTemplate.execute("OrderPort.orderItem(" + itemId + ")", () -> {
            orders.save(itemId);
            return null;
        });
    }

}
