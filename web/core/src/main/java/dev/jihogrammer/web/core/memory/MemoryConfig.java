package dev.jihogrammer.web.core.memory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MemoryCondition
@ComponentScan(basePackageClasses = MemoryController.class)
public class MemoryConfig {

    @Bean
    MemoryAgent memoryAgent() {
        return new MemoryAgent();
    }

}
