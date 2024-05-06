package dev.jihogrammer.spring.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class DatabaseConnectionUtilsTest {

    @Autowired
    DatabaseConnectionUtils utils;

    @Test
    void test() {
        // when
        var connection = utils.getConnection();
        log.info("connection={}; connectionClass={}", connection, connection.getClass());

        // then
        assertThat(connection).isNotNull();
    }

}
