package dev.jihogrammer.spring.boot.external.config;

import dev.jihogrammer.spring.boot.external.datasource.CustomDataSource;
import dev.jihogrammer.spring.boot.external.datasource.V3CustomDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(V3CustomDataSourceProperties.class)
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
