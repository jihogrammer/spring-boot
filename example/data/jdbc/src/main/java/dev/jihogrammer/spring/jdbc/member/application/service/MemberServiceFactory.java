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
    public SendMoneyPort sendMoneyPort(final MemberPort memberPort) {
        final var service = new TransactionalSendMoneyService(memberPort);

        log.info("Creating sendMoneyPort({}) with memberPort={}",
            service,
            memberPort);

        return service;
    }

    @Bean
    @Qualifier("transactionTemplateSendMoneyPort")
    public SendMoneyPort transactionTemplateSendMoneyPort(final MemberPort memberPort, final DataSource dataSource) {
        final var transactionManager = new DataSourceTransactionManager(dataSource);
        final var service = new TransactionTemplateSendMoneyService(memberPort, transactionManager);

        log.info("Creating sendMoneyPort({}) with memberPort={} and transactionManager={}",
            service,
            memberPort,
            transactionManager);

        return service;
    }

    @Bean
    @Qualifier("transactionManagerSendMoneyPort")
    public SendMoneyPort transactionManagerSendMoneyPort(final MemberPort memberPort, final DataSource dataSource) {
        final var transactionManager = new DataSourceTransactionManager(dataSource);
        final var service = new TransactionManagerSendMoneyService(memberPort, transactionManager);

        log.info("Creating sendMoneyPort({}) with memberPort={} and transactionManager={}",
            service,
            memberPort,
            transactionManager);

        return service;
    }

    @Bean
    @Qualifier("transactionHandlingSendMoneyPort")
    public SendMoneyPort transactionHandlingSendMoneyPort(
        @Qualifier("dataSourceMemberPort") final MemberPort memberPort,
        final DataSource dataSource
    ) {
        final var service = new TransactionHandlingSendMoneyService(memberPort, dataSource);

        log.info("Creating sendMoneyPort({}) with memberPort={} and dataSource={}",
            service,
            memberPort,
            dataSource);

        return service;
    }

    @Bean
    @Qualifier("unstableSendMoneyPort")
    public SendMoneyPort unstableSendMoneyPort(final MemberPort memberPort) {
        final var service = new UnstableSendMoneyService(memberPort);

        log.info("Creating sendMoneyPort({}) with memberPort={}", service, memberPort);

        return service;
    }

}
