package dev.jihogrammer.logtracer.adaptor.app.v2_component_scan;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class V2OrderController {

    private final OrderPort orderPort;

    @GetMapping("/v2/request")
    public String request(@RequestParam("itemId") final String itemId) {
        this.orderPort.orderItem(ItemId.of(itemId));
        return itemId;
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }

}
