package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
interface OrderController {

    @GetMapping("/v5/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v5/no-log")
    String noLog();

}
