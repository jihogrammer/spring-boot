package dev.jihogrammer.logtracer.adaptor.template.v1;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

@Repository
@RequiredArgsConstructor
class V1OrderAdaptor implements Orders {

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

            sleep();

            this.tracer.end(status);
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
