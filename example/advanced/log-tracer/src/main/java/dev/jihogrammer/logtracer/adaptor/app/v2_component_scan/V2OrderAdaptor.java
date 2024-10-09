package dev.jihogrammer.logtracer.adaptor.app.v2_component_scan;

import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;
import org.springframework.stereotype.Repository;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

@Repository
class V2OrderAdaptor implements Orders {

    @Override
    public void save(final ItemId itemId) {
        if (itemId == null) {
            throw new OrderException(new IllegalStateException("ItemId is null."));
        }
        if (ItemId.ExceptionItemId.class.isAssignableFrom(itemId.getClass())) {
            throw new OrderException(new IllegalStateException("ItemId is the ExceptionItemId."));
        }

        sleep();
    }

}
