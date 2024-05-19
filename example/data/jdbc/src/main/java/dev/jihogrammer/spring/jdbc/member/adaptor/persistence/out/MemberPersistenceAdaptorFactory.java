package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import com.zaxxer.hikari.HikariDataSource;
import dev.jihogrammer.spring.jdbc.connection.DatabaseConnectionUtils;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MemberPersistenceAdaptorFactory {

    // @Bean
    // spring.datasource.xxx 형식으로 설정하면 자동으로 DataSource 빈을 스프링이 알아서 등록한다.
    public DataSource dataSource(
        @Value("${spring.datasource.url}") final String url,
        @Value("${spring.datasource.username}") final String username,
        @Value("${spring.datasource.password:}") final String password
    ) {
        final var dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MemberPool");

        return dataSource;
    }

    @Bean
    @Primary
    @Qualifier("exceptionTranslatedMemberPort")
    public MemberPort exceptionTranslatedMemberPort(final DataSource dataSource) {
        return new StableDataSourceMemberAdaptor(dataSource);
    }

    @Bean
    @Qualifier("transactionManagerMemberPort")
    public MemberPort transactionManagerMemberPort(final DataSource dataSource) {
        return new MemberDataSourceTransactionManagerAdaptor(dataSource);
    }

    @Bean
    @Qualifier("dataSourceMemberPort")
    public MemberPort dataSourceMemberPort(final DataSource dataSource) {
        return new MemberDataSourceConnectionAdaptor(dataSource);
    }

    @Bean
    @Qualifier("jdbcMemberPort")
    public MemberPort jdbcMemberPort(final DatabaseConnectionUtils connectionUtils) {
        return new MemberJDBCAdaptor(connectionUtils);
    }

}
