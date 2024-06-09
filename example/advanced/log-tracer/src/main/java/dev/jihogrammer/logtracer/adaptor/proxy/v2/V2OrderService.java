package dev.jihogrammer.logtracer.adaptor.proxy.v2;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V2OrderService {

    private final V2OrderAdaptor orders;

    private final TraceTemplate traceTemplate;

    public V2OrderService(final V2OrderAdaptor orders, final Tracer tracer) {
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
