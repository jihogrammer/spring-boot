package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class V5OrderController implements OrderController {

    private final V5OrderService orderPort;

    @Override
    public String request(final String itemId) {
        orderPort.orderItem(ItemId.of(itemId));
        return itemId;
    }

    @Override
    public String noLog() {
        return null;
    }

}
