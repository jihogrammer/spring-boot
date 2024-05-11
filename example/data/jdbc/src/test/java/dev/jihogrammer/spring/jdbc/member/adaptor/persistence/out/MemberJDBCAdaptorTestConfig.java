package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.connection.DatabaseConnectionUtils;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@TestConfiguration
@RequiredArgsConstructor
public class MemberJDBCAdaptorTestConfig {

    private final DatabaseConnectionUtils utils;

    @PostConstruct
    void postConstruct() throws Throwable {
        try (
            final var connection = this.utils.getConnection();
            final var statement = connection.createStatement();
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
            throw new RuntimeException(e);
        }

        try (
            final var connection = this.utils.getConnection();
            final var statement = connection.createStatement();
        ) {
            final var rs = statement.executeQuery("SELECT COUNT(*) FROM MEMBER");

            while (rs.next()) {
                int value = rs.getInt(1);

                if (value == 0) {
                    log.info("Member table is created.");
                } else {
                    throw new RuntimeException("Failed to create member table.");
                }
            }
        } catch (Throwable e) {
            log.error("Failed to create member table.", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MemberPort memberPort() {
        return new MemberJDBCAdaptor(this.utils);
    }

}
