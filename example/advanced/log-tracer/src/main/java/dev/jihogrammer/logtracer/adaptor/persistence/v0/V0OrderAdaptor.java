package dev.jihogrammer.logtracer.adaptor.persistence.v0;

import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import org.springframework.stereotype.Repository;

@Repository
class V0OrderAdaptor implements Orders {

    @Override
    public void save(final ItemId itemId) {
        if (itemId == null) {
            throw new OrderException(new IllegalStateException("ItemId is null."));
        }
        if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
            throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
        }

        this.sleep(1_000);
    }

    private void sleep(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new OrderException(e);
        }
    }

}
