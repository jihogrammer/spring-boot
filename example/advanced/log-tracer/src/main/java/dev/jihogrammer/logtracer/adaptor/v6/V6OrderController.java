package dev.jihogrammer.logtracer.adaptor.v6;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V6OrderController implements OrderController {

    private final OrderPort orderPort;

    private final TraceTemplate traceTemplate;

    public V6OrderController(final OrderPort orderPort, final Tracer tracer) {
        this.orderPort = orderPort;
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public String request(final String itemId) {
        return this.traceTemplate.execute("OrderController.request(" + itemId + ")", () -> {
            orderPort.orderItem(ItemId.of(itemId));
            return itemId;
        });
    }

    @Override
    public String noLog() {
        return null;
    }

}
