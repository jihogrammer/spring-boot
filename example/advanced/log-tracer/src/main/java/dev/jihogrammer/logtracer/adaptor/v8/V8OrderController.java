package dev.jihogrammer.logtracer.adaptor.v8;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.TraceTemplate;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class V8OrderController {

    private final OrderPort orderPort;

    private final TraceTemplate traceTemplate;

    public V8OrderController(final OrderPort orderPort, final Tracer tracer) {
        this.orderPort = orderPort;
        this.traceTemplate = new TraceTemplate(tracer);
    }

    @GetMapping("/v8/request")
    public String request(@RequestParam("itemId") final String itemId) {
        return this.traceTemplate.execute("OrderController.request(" + itemId + ")", () -> {
            orderPort.orderItem(ItemId.of(itemId));
            return itemId;
        });
    }

    @GetMapping("/v7/no-log")
    public String noLog() {
        return null;
    }

}
