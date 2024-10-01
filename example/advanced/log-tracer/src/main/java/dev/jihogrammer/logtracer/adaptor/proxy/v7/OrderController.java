package dev.jihogrammer.logtracer.adaptor.proxy.v7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
interface OrderController {

    @GetMapping("/v7/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v7/no-log")
    String noLog();

}
