package dev.jihogrammer.items.infrastructure.adaptor.in;

import dev.jihogrammer.items.port.in.*;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsAdaptorInConfig {

    @Bean
    public ItemReadUsage itemReadUsage(final Items items) {
        return new ItemReadInteractor(items);
    }

    @Bean
    public ItemRegisterUsage itemRegisterUsage(final Items items) {
        return new ItemRegisterInteractor(items);
    }

    @Bean
    public ItemUpdateUsage itemUpdateUsage(final Items items) {
        return new ItemUpdateInteractor(items);
    }

}
