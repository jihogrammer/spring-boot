package dev.jihogrammer.spring.boot.memory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemoryController {

    private final MemoryFinder memoryFinder;

    @GetMapping("/health/memory")
    Memory memory() {
        final var memory = this.memoryFinder.find();
        log.info("memory={}", memory);
        return memory;
    }

}
