package dev.jihogrammer.logtracer.adaptor.v2;

import dev.jihogrammer.logtracer.application.port.in.SyncOrderPort;
import dev.jihogrammer.logtracer.application.port.in.SyncTracer;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class V2OrderController {

    private final SyncOrderPort orderPort;

    private final SyncTracer tracer;

    @GetMapping("/v2/request")
    public String request(@RequestParam("itemId") final String itemId) {
        final var status = this.tracer.start("OrderController.request(" + itemId + ")");

        try {
            this.orderPort.orderItem(status.traceId(), ItemId.of(itemId));

            this.tracer.end(status);

            return itemId;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
