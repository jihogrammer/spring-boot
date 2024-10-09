package dev.jihogrammer.logtracer.adaptor.proxy.v4;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class V4OrderController implements OrderController {

    private final OrderPort orderPort;

    @Override
    public String request(final String itemId) {
        this.orderPort.orderItem(ItemId.of(itemId));
        return itemId;
    }

    @Override
    public String noLog() {
        return null;
    }

}
