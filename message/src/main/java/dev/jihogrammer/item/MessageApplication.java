package dev.jihogrammer.item;

import dev.jihogrammer.items.port.in.ItemService;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
public class MessageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(new InMemoryItemRepository());
    }

}
