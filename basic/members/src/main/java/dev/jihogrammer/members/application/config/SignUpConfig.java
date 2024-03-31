package dev.jihogrammer.members.application.config;

import dev.jihogrammer.domain.members.port.in.SignUpInteractor;
import dev.jihogrammer.domain.members.port.in.SignUpUsage;
import dev.jihogrammer.domain.members.port.out.Members;
import dev.jihogrammer.members.application.signup.SignUpLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpConfig {

    @Bean
    public SignUpUsage signUpUsage(final Members members) {
        return new SignUpInteractor(members);
    }

    @Bean
    public SignUpLoggingAspect signUpLoggingAspect() {
        return new SignUpLoggingAspect();
    }

}
