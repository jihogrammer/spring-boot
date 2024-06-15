package dev.jihogrammer.logtracer.adaptor.proxy.v4;

import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.exception.OrderException;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

class V4OrderAdaptor implements Orders {

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
