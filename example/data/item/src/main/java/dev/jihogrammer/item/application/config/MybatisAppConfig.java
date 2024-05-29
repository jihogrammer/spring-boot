package dev.jihogrammer.item.application.config;

import dev.jihogrammer.item.adaptor.mybatis.ItemMapper;
import dev.jihogrammer.item.adaptor.mybatis.MybatisAdaptorFactory;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.application.service.ItemServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisAppConfig {

    @Bean
    public Items items(final ItemMapper itemMapper) {
        return new MybatisAdaptorFactory().mybatisItemAdaptor(itemMapper);
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
