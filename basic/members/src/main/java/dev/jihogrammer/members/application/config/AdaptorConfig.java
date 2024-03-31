package dev.jihogrammer.members.application.config;

import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.port.out.Members;
import dev.jihogrammer.members.infrastructure.adaptor.out.InMemoryMemberRepository;
import dev.jihogrammer.web.session.port.in.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AdaptorConfig {

    @Bean
    public Members members() {
        return new InMemoryMemberRepository(new ConcurrentHashMap<>());
    }

    @Bean
    public Session<Member> memberSession() {
        return new Session<>("signed-in-member");
    }

}
