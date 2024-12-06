package dev.jihogrammer.spring.boot.external;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SpringEnvironmentTest {

    @Test
    void testEnvironments() {
        // given
        var args = new String[]{"--name=jihogrammer", "--hello=world"};
        var context = SpringApplication.run(ExternalConfigApplication.class, args);

        // when
        System.getProperties().setProperty("coffee", "maxim");
        var environment = context.getBean(Environment.class);

        // then
        assertThat(environment.getProperty("name")).isEqualTo("jihogrammer");
        assertThat(environment.getProperty("hello")).isEqualTo("world");
        assertThat(environment.getProperty("coffee")).isEqualTo("maxim");
    }

    /**
     * Priority:
     * {@code Application Arguments > JVM Options > OS environment}
     */
    @Test
    void testEnvironmentPriority() {
        // given
        var args = new String[]{"--name=jihogrammer"};
        var context = SpringApplication.run(ExternalConfigApplication.class, args);

        // when
        System.getProperties().setProperty("name", "jiho");
        var environment = context.getBean(Environment.class);

        // then
        assertThat(environment.getProperty("name")).isEqualTo("jihogrammer");
    }

}
