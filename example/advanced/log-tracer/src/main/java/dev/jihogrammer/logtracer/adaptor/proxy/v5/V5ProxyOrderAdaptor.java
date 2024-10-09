package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V5ProxyOrderAdaptor extends V5OrderAdaptor {

    private final TraceTemplate traceTemplate;

    public V5ProxyOrderAdaptor(final Tracer tracer) {
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public void save(final ItemId itemId) {
        this.traceTemplate.execute("Orders.save(" + itemId + ")", () -> {
            super.save(itemId);
            return null;
        });
    }

}
