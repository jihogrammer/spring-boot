package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.application.port.in.MemberQuery;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import dev.jihogrammer.member.application.port.out.MemberPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberServiceConfig {

    @Bean
    public MemberSignUpUseCase memberSignUpUseCase(final MemberPort memberPort) {
        return new MemberSignUpService(memberPort);
    }

    @Bean
    public MemberQuery memberQuery(final MemberPort memberPort) {
        return new MemberQueryService(memberPort);
    }

}
