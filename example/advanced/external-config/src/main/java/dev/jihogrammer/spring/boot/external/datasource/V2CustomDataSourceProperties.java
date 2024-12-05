package dev.jihogrammer.spring.boot.external.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * 이상한 놈이다. gradle 사용하면 잘 돌아가니, 환경에서 잠시 gradle 빌드로 돌려서 사용해야 한다.
 */
@ConfigurationProperties("app.datasource")
public record V2CustomDataSourceProperties(
        String url,
        String username,
        String password,
        Config config
) {

    public record Config(int maxConnection, Duration timeout, List<String> options) {
    }

}
