package dev.jihogrammer.logtracer.adaptor.template.v3;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class V3OrderAdaptor implements Orders {

    private final Tracer tracer;

    @Override
    public void save(final ItemId itemId) {
        final var status = this.tracer.start("Orders.save(" + itemId + ")");

        try {
            if (itemId == null) {
                throw new OrderException(new IllegalStateException("ItemId is null."));
            }
            if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
                throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
            }

            this.sleep((long) (Math.random() * 1000));

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
