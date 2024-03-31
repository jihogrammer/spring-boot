package dev.jihogrammer.web.core.memory;

import dev.jihogrammer.web.core.ConditionalController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemoryController implements ConditionalController {

    private final MemoryAgent memoryAgent;

    @GetMapping("/_memory")
    public Memory memory() {
        return this.memoryAgent.check();
    }

}
