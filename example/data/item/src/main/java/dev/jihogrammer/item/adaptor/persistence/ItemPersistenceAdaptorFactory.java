package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.application.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ItemPersistenceAdaptorFactory {

    @Bean
    public Items inMemoryItemAdaptor() {
        return new InMemoryItemAdaptor(new ConcurrentHashMap<>());
    }

}
