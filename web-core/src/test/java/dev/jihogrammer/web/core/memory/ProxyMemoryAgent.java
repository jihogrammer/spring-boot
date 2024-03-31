package dev.jihogrammer.web.core.memory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyMemoryAgent extends MemoryAgent {

    @Override
    public Memory check() {
        log.info("Start to check JVM memory.");
        Memory memory = super.check();
        log.info("JVM memory: {}", memory);

        return memory;
    }

}
