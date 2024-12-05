package dev.jihogrammer.spring.boot.external.config;

import dev.jihogrammer.spring.boot.external.datasource.CustomDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Configuration
public class DataSourceValueConfig {

    @Bean
    CustomDataSource dataSource(
            @Value("${app.datasource.url}") final String url,
            @Value("${app.datasource.username}") final String username,
            @Value("${app.datasource.password}") final String password,
            @Value("${app.datasource.config.max-connection}") final int maxConnection,
            @Value("${app.datasource.config.timeout}") final Duration timeout,
            @Value("${app.datasource.config.options}") final List<String> options
    ) {
        return new CustomDataSource(url, username, password, maxConnection, timeout, options);
    }

}
