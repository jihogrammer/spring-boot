package dev.jihogrammer.spring.boot.autoconfig.memory.adaptor;

import dev.jihogrammer.spring.boot.memory.MemoryController;
import dev.jihogrammer.spring.boot.memory.MemoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MemoryConfig {

    @Bean
    MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    MemoryController memoryController(final MemoryFinder memoryFinder) {
        return new MemoryController(memoryFinder);
    }

}
