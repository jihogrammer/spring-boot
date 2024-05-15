package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberServiceFactory {

    @Bean
    public SendMoneyPort sendMoneyPort(final MemberPort memberPort) {
        return new SendMoneyService(memberPort);
    }

}
