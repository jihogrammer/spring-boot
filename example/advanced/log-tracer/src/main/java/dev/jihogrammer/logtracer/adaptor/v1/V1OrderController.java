package dev.jihogrammer.logtracer.adaptor.v1;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class V1OrderController {

    private final OrderPort orderPort;

    private final Tracer tracer;

    @GetMapping("/v1/request")
    public String request(@RequestParam("itemId") final String itemId) {
        final var status = this.tracer.start("OrderController.request(" + itemId + ")");

        try {
            this.orderPort.orderItem(ItemId.of(itemId));

            this.tracer.end(status);

            return itemId;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
