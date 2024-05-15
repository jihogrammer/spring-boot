package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import com.zaxxer.hikari.HikariDataSource;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MemberPersistenceAdaptorFactory {

    @Bean
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
    public MemberPort memberPort(final DataSource dataSource) {
        return new MemberDataSourceConnectionAdaptor(dataSource);
    }

}
