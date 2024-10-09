package dev.jihogrammer.logtracer.adaptor.proxy.v4;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class V4LoggingProxyOrderController implements OrderController {

    private final OrderController delegate;

    private final Tracer tracer;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = this.tracer.start("OrderController.request(%s)".formatted(itemId));
            var result = this.delegate.request(itemId);
            this.tracer.end(status);

            return result;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return this.delegate.noLog();
    }
}
