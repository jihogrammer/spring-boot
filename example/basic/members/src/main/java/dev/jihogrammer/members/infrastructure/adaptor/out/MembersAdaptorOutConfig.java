package dev.jihogrammer.members.infrastructure.adaptor.out;

import dev.jihogrammer.domain.members.port.out.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MembersAdaptorOutConfig {

    @Bean
    public Members members() {
        return new InMemoryMemberRepository(new ConcurrentHashMap<>());
    }

}
