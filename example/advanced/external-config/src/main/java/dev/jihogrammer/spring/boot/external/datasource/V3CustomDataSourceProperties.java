package dev.jihogrammer.spring.boot.external.datasource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * 동일하게 gradle 빌드로 진행해야 하고, java record 사용하면 제대로 동작하지 않는다.
 */
@ConfigurationProperties("app.datasource")
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class V3CustomDataSourceProperties {

    @NotBlank
    private final String url;

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    private final Config config;

    @RequiredArgsConstructor
    @Getter
    @Accessors(fluent = true)
    public static class Config {

        @Min(1)
        @Max(999)
        private final int maxConnection;

        @DurationMin(seconds = 1)
        @DurationMax(seconds = 60)
        private final Duration timeout;

        private final List<String> options;

    }

}
