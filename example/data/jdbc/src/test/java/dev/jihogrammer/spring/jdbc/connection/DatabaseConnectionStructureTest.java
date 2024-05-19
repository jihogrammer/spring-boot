package dev.jihogrammer.spring.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(classes = ConnectionDataTestConfig.class)
class DatabaseConnectionStructureTest {

    @Autowired
    ConnectionDataTestConfig config;

    /**
     * Java 내부 JDBC 사용을 위한 방법
     * <br>
     * 항상 새로운 Connection 객체를 생성한다.
     *
     * @see DriverManager
     */
    @Test
    void testDriverManagerConnection() throws Throwable {
        // given
        Connection connection1;
        Connection connection2;

        // when
        connection1 = DriverManager.getConnection(config.url(), config.username(), config.password());
        connection2 = DriverManager.getConnection(config.url(), config.username(), config.password());
        log.info("java.sql.DriverManager#getConnection -> {}", connection1);
        log.info("java.sql.DriverManager#getConnection -> {}", connection2);

        // then
        assertThat(connection1).isNotNull();
        assertThat(connection2).isNotNull();
        assertThat(connection1).isNotEqualTo(connection2);
    }

    /**
     * Spring 내부 JDBC 사용을 위한 방법
     * <br>
     * java.sql.DriverManager 방식과 마찬가지로 항상 새로운 Connection 객체를 생성한다.
     *
     * @see DriverManagerDataSource
     */
    @Test
    void testDriverManagerDataSourceConnection() throws Throwable {
        // given
        Connection connection1;
        Connection connection2;
        var dataSource = new DriverManagerDataSource(config.url(), config.username(), config.password());

        // when
        connection1 = dataSource.getConnection();
        connection2 = dataSource.getConnection();
        log.info("DriverManagerDataSource#getConnection -> {}", connection1);
        log.info("DriverManagerDataSource#getConnection -> {}", connection2);

        // then
        assertThat(connection1).isNotNull();
        assertThat(connection2).isNotNull();
        assertThat(connection1).isNotEqualTo(connection2);
    }

    @Test
    void testHikariDataSourceConnection() throws Throwable {
        // given
        Connection connection1;
        Connection connection2;
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.url());
        dataSource.setUsername(config.username());
        dataSource.setPassword(config.password());
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("TestPool");

        // when
        connection1 = dataSource.getConnection();
        connection2 = dataSource.getConnection();
        log.info("HikariDataSource#getConnection -> {}", connection1);
        log.info("HikariDataSource#getConnection -> {}", connection2);
        Thread.sleep(1000);

        // then
        assertThat(connection1).isNotNull();
        assertThat(connection2).isNotNull();
        assertThat(connection1).isNotEqualTo(connection2);

        // post
        dataSource.close();
    }

}
