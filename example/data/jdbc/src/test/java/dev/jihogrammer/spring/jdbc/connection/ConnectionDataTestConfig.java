package dev.jihogrammer.spring.jdbc.connection;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@Getter
@Accessors(fluent = true)
public class ConnectionDataTestConfig {

    private final String url;

    private final String username;

    private final String password;

    public ConnectionDataTestConfig(
        @Value("${spring.datasource.url}") final String url,
        @Value("${spring.datasource.username}") final String username,
        @Value("${spring.datasource.password:}") final String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

}
