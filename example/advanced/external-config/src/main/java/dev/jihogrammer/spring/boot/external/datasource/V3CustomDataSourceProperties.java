package dev.jihogrammer.spring.boot.external.datasource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;

/**
 * 레코드로 변환 후에도 제대로 동작하는 것이 확인됨.
 * 다만, 하위 레코드에도 {@code @ConfigurationProperties, @Validated} 어노테이션을 붙여주어야 기대한 동작을 수행한다.
 */
@ConfigurationProperties("app.datasource")
@Validated
public record V3CustomDataSourceProperties(
        @NotBlank
        String url,
        @NotBlank
        String username,
        @NotBlank
        String password,
        Config config
) {

    @ConfigurationProperties("app.datasource.config")
    @Validated
    public record Config(
            @Min(1)
            @Max(999)
            int maxConnection,
            @DurationMin(seconds = 1)
            @DurationMax(seconds = 60)
            Duration timeout,
            List<String> options
    ) {
    }

}
