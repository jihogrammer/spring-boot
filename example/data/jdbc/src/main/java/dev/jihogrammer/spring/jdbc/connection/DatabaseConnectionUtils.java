package dev.jihogrammer.spring.jdbc.connection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnectionUtils {

    private final String url;

    private final String username;

    private final String password;

    public DatabaseConnectionUtils(
        @Value("${spring.datasource.url}") final String url,
        @Value("${spring.datasource.username}") final String username,
        @Value("${spring.datasource.password:}") final String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
