package dev.jihogrammer.items.infrastructure.adaptor.out;

import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsAdaptorOutConfig {

    @Bean
    public Items items() {
        return new InMemoryItemRepository();
    }

}
