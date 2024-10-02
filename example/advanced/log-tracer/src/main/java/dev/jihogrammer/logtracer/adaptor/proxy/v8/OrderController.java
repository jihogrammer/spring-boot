package dev.jihogrammer.logtracer.adaptor.proxy.v8;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class OrderController {

    private final OrderPort orderPort;

    @GetMapping("/v8/request")
    public String request(@RequestParam("itemId") final String itemId) {
        orderPort.orderItem(ItemId.of(itemId));
        return itemId;
    }

    @GetMapping("/v8/no-log")
    public String noLog() {
        return null;
    }

}
