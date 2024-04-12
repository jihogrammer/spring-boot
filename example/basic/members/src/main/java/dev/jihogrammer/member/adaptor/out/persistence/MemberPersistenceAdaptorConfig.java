package dev.jihogrammer.member.adaptor.out.persistence;

import dev.jihogrammer.member.application.port.out.MemberPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberPersistenceAdaptorConfig {

    @Bean
    public MemberPort members() {
        return new InMemoryMemberAdaptor();
    }

}
