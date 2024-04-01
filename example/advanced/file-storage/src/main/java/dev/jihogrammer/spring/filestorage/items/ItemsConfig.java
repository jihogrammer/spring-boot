package dev.jihogrammer.spring.filestorage.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsConfig {

    @Bean
    public Items items() {
        return new ItemsInMemoryRepository();
    }

}
