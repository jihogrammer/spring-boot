package dev.jihogrammer.spring.boot.external;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class EnvironmentLoggingTest {

    @Test
    void osEnvironment() {
        for (final var entry : System.getenv().entrySet()) {
            log.info("{}={}", entry.getKey(), entry.getValue());
        }
    }

    @Test
    void vmEnvironment() {
        for (final var entry : System.getProperties().entrySet()) {
            log.info("{}={}", entry.getKey(), entry.getValue());
        }
    }

    @Test
    void argsEnvironment() {
        // given
        var args = new String[] {"hello", "world", "--name=jihogrammer"};

        // when
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("sourceArgs={}", List.of(appArgs.getSourceArgs()));

        // then
        assertThat(appArgs.getNonOptionArgs()).contains("hello", "world");
        assertThat(appArgs.getOptionNames()).contains("name");
        assertThat(appArgs.getOptionValues("name")).contains("jihogrammer");
    }

}
