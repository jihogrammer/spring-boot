package dev.jihogrammer.spring.boot.external.config;

import dev.jihogrammer.spring.boot.external.datasource.CustomDataSource;
import dev.jihogrammer.spring.boot.external.datasource.V1CustomDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@EnableConfigurationProperties(V1CustomDataSourceProperties.class)
public class DataSourceV1PropertiesConfig {

    @Bean
    CustomDataSource dataSource(final V1CustomDataSourceProperties properties) {
        return new CustomDataSource(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getConfig().getMaxConnection(),
                properties.getConfig().getTimeout(),
                properties.getConfig().getOptions());
    }

}
