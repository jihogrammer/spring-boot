package dev.jihogrammer.item.application.config;

import dev.jihogrammer.item.adaptor.jpa.ItemJpaAdaptorFactory;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.application.service.ItemServiceFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaAppConfig {

    @Bean
    public Items items(final EntityManager entityManager) {
        return new ItemJpaAdaptorFactory().items(entityManager);
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
