package dev.jihogrammer.logtracer.adaptor.v2;

import dev.jihogrammer.logtracer.application.port.in.SyncOrderPort;
import dev.jihogrammer.logtracer.application.port.in.SyncTracer;
import dev.jihogrammer.logtracer.application.port.out.SyncOrders;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class V2OrderService implements SyncOrderPort {

    private final SyncOrders orders;

    private final SyncTracer tracer;

    @Override
    public void orderItem(final ItemId itemId) {
        this.orders.save(itemId);
    }

    @Override
    public void orderItem(final TraceId prevId, final ItemId itemId) {
        final var status = this.tracer.start(prevId, "SyncOrderPort.orderItem(" + prevId + "; " + itemId + ")");

        try {
            this.orders.save(status.traceId(), itemId);

            this.tracer.end(status);
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
