package dev.jihogrammer.items;

import dev.jihogrammer.items.port.in.ItemService;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
public class ItemsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(new InMemoryItemRepository());
    }

}
