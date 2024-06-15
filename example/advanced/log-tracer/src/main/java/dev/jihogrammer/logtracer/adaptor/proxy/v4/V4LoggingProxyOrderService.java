package dev.jihogrammer.logtracer.adaptor.proxy.v4;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class V4LoggingProxyOrderService implements OrderPort {

    private final OrderPort delegate;

    private final Tracer tracer;

    @Override
    public void orderItem(ItemId itemId) {
        TraceStatus status = null;
        try {
            status = this.tracer.start("OrderPort.orderItem(%s)".formatted(itemId));
            this.delegate.orderItem(itemId);
            this.tracer.end(status);
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }
}
