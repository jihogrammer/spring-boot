package dev.jihogrammer.gateway.config;

import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import dev.jihogrammer.domain.members.port.out.Members;
import dev.jihogrammer.items.port.in.*;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public Members members() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public Items items() {
        return new InMemoryItemRepository();
    }

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
