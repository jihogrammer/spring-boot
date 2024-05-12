package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@TestConfiguration
public class MemberDriverManagerDataSourceAdaptorTestConfig {

    private final DataSource dataSource;

    public MemberDriverManagerDataSourceAdaptorTestConfig(
        @Value("${spring.datasource.url}") final String url,
        @Value("${spring.datasource.username}") final String username,
        @Value("${spring.datasource.password:}") final String password
    ) {
        this.dataSource = new DriverManagerDataSource(url, username, password);
    }

    @PostConstruct
    void postConstruct() throws Throwable {
        try (
            final var connection = this.dataSource.getConnection();
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
    }

    @Bean
    public MemberPort memberPort() {
        return new MemberDataSourceAdaptor(this.dataSource);
    }

}
