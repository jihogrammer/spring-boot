package dev.jihogrammer.spring.boot.actuator.etc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/_log")
public class LogController {

    @GetMapping("/run")
    Map<String, String> executeLog() {
        log.trace("A trace log.");
        log.debug("A debug log.");
        log.info("A info log.");
        log.warn("A warn log.");
        log.error("A error log.");

        return Map.of("status", "OK");
    }

}
