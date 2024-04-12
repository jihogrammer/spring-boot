package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.application.port.in.MemberSignInUseCase;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import dev.jihogrammer.member.application.port.out.MemberPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberServiceConfig {

    @Bean
    public MemberSignInUseCase signInUsage(final MemberPort memberPort) {
        return new MemberSignInService(memberPort);
    }

    @Bean
    public MemberSignUpUseCase signUpUsage(final MemberPort memberPort) {
        return new MemberSignUpService(memberPort);
    }

}
