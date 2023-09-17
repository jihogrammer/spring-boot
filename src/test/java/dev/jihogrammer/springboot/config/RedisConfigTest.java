package dev.jihogrammer.springboot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {RedisConfig.class})
class RedisConfigTest {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void ping() {
        // given
        RedisConnection connection = redisConnectionFactory.getConnection();
        // when
        String pong = connection.ping();
        // then
        assertThat(pong).isEqualToIgnoringCase("pong");
    }
}