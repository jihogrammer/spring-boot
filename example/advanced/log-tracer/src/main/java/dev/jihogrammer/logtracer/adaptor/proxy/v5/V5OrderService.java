package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class V5OrderService {

    private final V5OrderAdaptor orders;

    public void orderItem(final ItemId itemId) {
        orders.save(itemId);
    }

}
