package dev.jihogrammer.members.application.config;

import dev.jihogrammer.domain.members.port.in.SignInInteractor;
import dev.jihogrammer.domain.members.port.in.SignInUsage;
import dev.jihogrammer.domain.members.port.out.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignInConfig {

    @Bean
    public SignInUsage signInUsage(final Members members) {
        return new SignInInteractor(members);
    }

}
