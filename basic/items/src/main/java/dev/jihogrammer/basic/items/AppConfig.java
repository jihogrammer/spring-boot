package dev.jihogrammer.basic.items;

import dev.jihogrammer.items.port.in.ItemService;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Items items() {
        return new InMemoryItemRepository();
    }

    @Bean
    public ItemService itemService(final Items items) {
        return new ItemService(items);
    }

}
