package dev.jihogrammer.logtracer.adaptor.proxy.v6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
interface OrderController {

    @GetMapping("/v6/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v6/no-log")
    String noLog();

}
