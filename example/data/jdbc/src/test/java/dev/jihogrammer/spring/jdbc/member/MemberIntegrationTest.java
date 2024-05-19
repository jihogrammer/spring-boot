package dev.jihogrammer.spring.jdbc.member;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@SpringBootTest
public abstract class MemberIntegrationTest {

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected MemberPort memberPort;

    @Autowired
    @Qualifier("exceptionTranslatedMemberPort")
    protected MemberPort exceptionTranslatedMemberPort;

    @Autowired
    @Qualifier("dataSourceMemberPort")
    protected MemberPort dataSourceMemberPort;

    @Autowired
    @Qualifier("jdbcMemberPort")
    protected MemberPort jdbcMemberPort;

    @Autowired
    @Qualifier("exceptionTranslatedSendMoneyPort")
    protected SendMoneyPort exceptionTranslatedSendMoneyPort;

    @Autowired
    @Qualifier("transactionalSendMoneyPort")
    protected SendMoneyPort transactionalSendMoneyPort;

    @Autowired
    @Qualifier("transactionTemplateSendMoneyPort")
    protected SendMoneyPort transactionTemplateSendMoneyPort;

    @Autowired
    @Qualifier("transactionManagerSendMoneyPort")
    protected SendMoneyPort transactionManagerSendMoneyPort;

    @Autowired
    @Qualifier("transactionHandlingSendMoneyPort")
    protected SendMoneyPort transactionHandlingSendMoneyPort;

    @Autowired
    @Qualifier("unstableSendMoneyPort")
    protected SendMoneyPort unstableSendMoneyPort;

    @PostConstruct
    void createMemberTable() throws SQLException {
        try (
            final var connection = this.dataSource.getConnection();
            final var statement = connection.createStatement()
        ) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS MEMBER (
                        MEMBER_ID VARCHAR(100) NOT NULL,
                        MONEY INTEGER NOT NULL,
                        PRIMARY KEY (MEMBER_ID)
                    )
            """);
        } catch (Throwable e) {
            log.error("Failed to create member table.", e);
            throw e;
        }
    }

}
