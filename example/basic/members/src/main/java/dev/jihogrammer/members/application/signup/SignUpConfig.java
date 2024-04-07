package dev.jihogrammer.members.application.signup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpConfig {

    @Bean
    public SignUpLoggingAspect signUpLoggingAspect() {
        return new SignUpLoggingAspect();
    }

}
