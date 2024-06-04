package dev.jihogrammer.item.application.config;

import dev.jihogrammer.item.adaptor.querydsl.ItemQueryDSLAdaptorFactory;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.application.service.ItemServiceFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDSLAppConfig {

    @Bean
    public Items items(final EntityManager entityManager) {
        return new ItemQueryDSLAdaptorFactory().items(entityManager);
    }

    @Bean
    public ItemQuery itemQuery(final Items items) {
        return new ItemServiceFactory().itemQuery(items);
    }

    @Bean
    public ItemUpdatePort itemUpdatePort(final Items items) {
        return new ItemServiceFactory().itemUpdatePort(items);
    }

}
