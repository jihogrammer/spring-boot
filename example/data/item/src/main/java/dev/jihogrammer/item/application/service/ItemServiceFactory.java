package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemServiceFactory {

    @Bean
    public ItemQuery itemQuery(final Items items) {
        return new ItemQueryService(items);
    }

    @Bean
    public ItemUpdatePort itemUpdatePort(final Items items) {
        return new ItemUpdateService(items);
    }

}
