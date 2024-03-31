package dev.jihogrammer.web.core.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name = "web.core.memory", havingValue = "on")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryAgent memoryAgent;

    @GetMapping("/_memory")
    public Memory memory() {
        return this.memoryAgent.check();
    }

}
