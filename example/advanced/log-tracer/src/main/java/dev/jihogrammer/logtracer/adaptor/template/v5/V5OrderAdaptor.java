package dev.jihogrammer.logtracer.adaptor.template.v5;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import org.springframework.stereotype.Repository;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

@Repository
class V5OrderAdaptor implements Orders {

    private final TraceTemplate traceTemplate;

    public V5OrderAdaptor(final Tracer tracer) {
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
