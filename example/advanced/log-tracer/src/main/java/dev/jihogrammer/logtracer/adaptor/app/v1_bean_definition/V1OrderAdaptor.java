package dev.jihogrammer.logtracer.adaptor.app.v1_bean_definition;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

class V1OrderAdaptor implements Orders {

    private final TraceTemplate traceTemplate;

    public V1OrderAdaptor(final Tracer tracer) {
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public void save(final ItemId itemId) {
        this.traceTemplate.execute("Orders.save(" + itemId + ")", () -> {
            if (itemId == null) {
                throw new OrderException(new IllegalStateException("ItemId is null."));
            }
            if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
                throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
            }

            sleep();

            return null;
        });
    }

}
