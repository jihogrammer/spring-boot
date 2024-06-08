package dev.jihogrammer.logtracer.adaptor.v2;

import dev.jihogrammer.logtracer.application.port.in.SyncTracer;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.port.out.SyncOrders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class V2OrderAdaptor implements SyncOrders {

    private final SyncTracer tracer;

    @Override
    public void save(final ItemId itemId) {
        if (itemId == null) {
            throw new OrderException(new IllegalStateException("ItemId is null."));
        }
        if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
            throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
        }

        this.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void save(final TraceId prevId, final ItemId itemId) {
        final var status = this.tracer.start(prevId, "SyncOrders.save(" + prevId + "; " + itemId + ")");

        try {
            this.save(itemId);
            this.tracer.end(status);
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

    private void sleep(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new OrderException(e);
        }
    }

}
