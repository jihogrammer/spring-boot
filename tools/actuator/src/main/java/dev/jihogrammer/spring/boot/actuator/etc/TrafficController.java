package dev.jihogrammer.spring.boot.actuator.etc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/_traffic")
class TrafficController {

    @GetMapping("/stress")
    Map<String, Object> stress(@RequestParam("loop-count") final long loopCount) {
        log.info("Start doing stress: {} count", loopCount);

        for (long i = 1; i <= loopCount; i++) {
            log.trace("stress loop count: {}", i);
        }

        log.info("Finished doing stress: {} count", loopCount);

        return Map.of("status", "OK", "loopCount", loopCount);
    }

}
