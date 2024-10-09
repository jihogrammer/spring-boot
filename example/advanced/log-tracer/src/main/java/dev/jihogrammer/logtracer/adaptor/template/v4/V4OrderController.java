package dev.jihogrammer.logtracer.adaptor.template.v4;

import dev.jihogrammer.logtracer.application.port.in.AbstractTemplate;
import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class V4OrderController {

    private final OrderPort orderPort;

    private final Tracer tracer;

    @GetMapping("/v4/request")
    public String request(@RequestParam("itemId") final String itemId) {
        var template = new AbstractTemplate<String>(tracer) {
            @Override
            protected String call() {
                orderPort.orderItem(ItemId.of(itemId));
                return itemId;
            }
        };
        return template.execute("OrderController.request(" + itemId + ")");
    }

}
