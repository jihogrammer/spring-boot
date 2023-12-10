package dev.jihogrammer.items.app;

import dev.jihogrammer.items.db.InMemoryItemRepository;
import dev.jihogrammer.items.domain.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Items items() {
        return new InMemoryItemRepository(new ConcurrentHashMap<>(), new AtomicLong());
    }
}
