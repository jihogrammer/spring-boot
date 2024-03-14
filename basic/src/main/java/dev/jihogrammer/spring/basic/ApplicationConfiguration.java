package dev.jihogrammer.spring.basic;

import dev.jihogrammer.items.port.in.ItemService;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ItemService itemService() {
        return new ItemService(new InMemoryItemRepository());
    }

}
