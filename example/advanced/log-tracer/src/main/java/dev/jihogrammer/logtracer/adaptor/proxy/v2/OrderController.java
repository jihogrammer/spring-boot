package dev.jihogrammer.logtracer.adaptor.proxy.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
interface OrderController {

    @GetMapping("/v2/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v2/no-log")
    String noLog();

}
