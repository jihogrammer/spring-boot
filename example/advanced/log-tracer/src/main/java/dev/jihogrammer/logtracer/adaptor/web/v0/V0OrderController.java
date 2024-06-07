package dev.jihogrammer.logtracer.adaptor.web.v0;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class V0OrderController {

    private final OrderPort orderPort;

    @GetMapping("/v0/request")
    public String request(@RequestParam("itemId") final String itemId) {
        this.orderPort.orderItem(ItemId.of(itemId));
        return itemId;
    }

}
