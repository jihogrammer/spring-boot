package dev.jihogrammer.gateway.config;

import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import dev.jihogrammer.domain.members.port.out.Members;
import dev.jihogrammer.items.port.in.ItemService;
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
    public ItemService itemService(final Items items) {
        return new ItemService(items);
    }

}
