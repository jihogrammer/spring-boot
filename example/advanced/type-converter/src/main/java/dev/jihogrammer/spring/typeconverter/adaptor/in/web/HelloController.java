package dev.jihogrammer.spring.typeconverter.adaptor.in.web;

import dev.jihogrammer.spring.typeconverter.adaptor.in.web.entity.InternetProtocolAndPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(final HttpServletRequest request) {
        String rawData = request.getParameter("data");
        Integer integerData = Integer.valueOf(rawData);
        log.info("data = {}", integerData);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam("data") final Integer data) {
        log.info("data = {}", data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam("ipPort") final InternetProtocolAndPort ipPort) {
        log.info("ip: {}", ipPort.ip());
        log.info("port: {}", ipPort.port());
        return "ok";
    }

}
