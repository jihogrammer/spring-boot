package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MemberServiceFactory {

    @Bean
    @Qualifier("unstableSendMoneyPort")
    public SendMoneyPort unstableSendMoneyPort(final MemberPort memberPort) {
        return new UnstableSendMoneyService(memberPort);
    }

    @Bean
    @Primary
    @Qualifier("transactionHandlingSendMoneyPort")
    public SendMoneyPort transactionHandlingSendMoneyPort(final DataSource dataSource, final MemberPort memberPort) {
        return new TransactionHandlingSendMoneyService(dataSource, memberPort);
    }

}
