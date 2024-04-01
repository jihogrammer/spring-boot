package dev.jihogrammer.members.infrastructure.adaptor.in;

import dev.jihogrammer.domain.members.port.in.SignInInteractor;
import dev.jihogrammer.domain.members.port.in.SignInUsage;
import dev.jihogrammer.domain.members.port.in.SignUpInteractor;
import dev.jihogrammer.domain.members.port.in.SignUpUsage;
import dev.jihogrammer.domain.members.port.out.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdaptorInConfig {

    @Bean
    public SignUpUsage signUpUsage(final Members members) {
        return new SignUpInteractor(members);
    }

    @Bean
    public SignInUsage signInUsage(final Members members) {
        return new SignInInteractor(members);
    }

}
