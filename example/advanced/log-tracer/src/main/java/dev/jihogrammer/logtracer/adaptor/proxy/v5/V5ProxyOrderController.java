package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;

class V5ProxyOrderController extends V5OrderController {

    private final TraceTemplate traceTemplate;

    public V5ProxyOrderController(final V5OrderService service, final Tracer tracer) {
        super(service);
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @Override
    public String request(final String itemId) {
        return this.traceTemplate.execute(
                "OrderController.request(" + itemId + ")",
                () -> super.request(itemId));
    }

    @Override
    public String noLog() {
        return null;
    }

}
