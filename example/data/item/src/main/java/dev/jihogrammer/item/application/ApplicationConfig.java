package dev.jihogrammer.item.application;

import dev.jihogrammer.item.adaptor.local.LocalDataInitializer;
import dev.jihogrammer.item.adaptor.persistence.ItemPersistenceAdaptorFactory;
import dev.jihogrammer.item.adaptor.web.HomeController;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.application.service.ItemServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = HomeController.class)
public class ApplicationConfig {

    @Bean
    public Items items(final DataSource dataSource) {
//        return new ItemPersistenceAdaptorFactory().inMemoryItemAdaptor();
        return new ItemPersistenceAdaptorFactory().jdbcTemplateItemAdaptor(dataSource);
    }

    @Bean
    public ItemQuery itemQuery(final Items items) {
        return new ItemServiceFactory().itemQuery(items);
    }

    @Bean
    public ItemUpdatePort itemUpdatePort(final Items items) {
        return new ItemServiceFactory().itemUpdatePort(items);
    }

    @Bean
    @Profile("local")
    public LocalDataInitializer localDataInitializer(final ItemUpdatePort itemUpdatePort) {
        return new LocalDataInitializer(itemUpdatePort);
    }

}
