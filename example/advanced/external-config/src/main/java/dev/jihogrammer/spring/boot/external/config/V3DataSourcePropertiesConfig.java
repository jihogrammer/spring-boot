package dev.jihogrammer.spring.boot.external.config;

import dev.jihogrammer.spring.boot.external.datasource.CustomDataSource;
import dev.jihogrammer.spring.boot.external.datasource.V3CustomDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 하위 레코드에도 검증을 수행하기 위해 {@code @EnableConfigurationProperties} 인자로 하위 레코드도 넣어주어야 기대하는 동작을 수행한다.
 */
@EnableConfigurationProperties({V3CustomDataSourceProperties.class, V3CustomDataSourceProperties.Config.class})
public class V3DataSourcePropertiesConfig {

    @Bean
    CustomDataSource dataSource(final V3CustomDataSourceProperties properties) {
        return new CustomDataSource(
                properties.url(),
                properties.username(),
                properties.password(),
                properties.config().maxConnection(),
                properties.config().timeout(),
                properties.config().options());
    }

}
