package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class MemberServiceFactory {

    @Bean
    @Primary
    public SendMoneyPort sendMoneyPort(final MemberPort memberPort, final DataSource dataSource) {
        final var transactionManager = new DataSourceTransactionManager(dataSource);

        log.info("Creating sendMoneyPort with memberPort={} and transactionManager={}", memberPort, transactionManager);
        return new TransactionManagerSendMoneyService(memberPort, transactionManager);
    }

    @Bean
    @Qualifier("transactionHandlingSendMoneyPort")
    public SendMoneyPort transactionHandlingSendMoneyPort(
        @Qualifier("dataSourceMemberPort") final MemberPort memberPort,
        final DataSource dataSource
    ) {
        log.info("Creating sendMoneyPort with memberPort={} and dataSource={}", memberPort, dataSource);
        return new TransactionHandlingSendMoneyService(memberPort, dataSource);
    }

    @Bean
    @Qualifier("unstableSendMoneyPort")
    public SendMoneyPort unstableSendMoneyPort(final MemberPort memberPort) {
        log.info("Creating sendMoneyPort with memberPort={}", memberPort);
        return new UnstableSendMoneyService(memberPort);
    }

}
