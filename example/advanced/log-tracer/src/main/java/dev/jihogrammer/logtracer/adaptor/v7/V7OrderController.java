package dev.jihogrammer.logtracer.adaptor.v7;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;

class V7OrderController implements OrderController {

    private final V7OrderService orderPort;

    private final TraceTemplate traceTemplate;

    public V7OrderController(final V7OrderService orderPort, final Tracer tracer) {
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
