package dev.jihogrammer.spring.boot.memory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
@ConditionalOnProperty(name = "memory", havingValue = "on")
class MemoryAutoConfig {

    @PostConstruct
    void postConstruct() {
        log.info("Memory Auto Configuration is applied.");
    }

    @Bean
    MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    MemoryController memoryController(final MemoryFinder memoryFinder) {
        return new MemoryController(memoryFinder);
    }

}
