package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V5ProxyOrderService extends V5OrderService {

    private final TraceTemplate traceTemplate;

    public V5ProxyOrderService(final V5OrderAdaptor adaptor, final Tracer tracer) {
        super(adaptor);
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public void orderItem(final ItemId itemId) {
        this.traceTemplate.execute("OrderPort.orderItem(" + itemId + ")", () -> {
            super.orderItem(itemId);
            return null;
        });
    }

}
