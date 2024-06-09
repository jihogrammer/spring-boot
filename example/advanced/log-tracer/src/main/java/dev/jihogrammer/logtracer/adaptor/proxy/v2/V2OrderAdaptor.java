package dev.jihogrammer.logtracer.adaptor.proxy.v2;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;

class V2OrderAdaptor {

    private final TraceTemplate traceTemplate;

    public V2OrderAdaptor(final Tracer tracer) {
        this.traceTemplate = new TraceTemplate(tracer);
    }

    public void save(final ItemId itemId) {
        this.traceTemplate.execute("Orders.save(" + itemId + ")", () -> {
            if (itemId == null) {
                throw new OrderException(new IllegalStateException("ItemId is null."));
            }
            if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
                throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
            }

            sleep((long) (Math.random() * 1000));

            return null;
        });
    }

    private void sleep(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new OrderException(e);
        }
    }

}
