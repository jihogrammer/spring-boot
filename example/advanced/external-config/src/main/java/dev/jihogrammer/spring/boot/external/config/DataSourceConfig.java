package dev.jihogrammer.spring.boot.external.config;

import dev.jihogrammer.spring.boot.external.datasource.CustomDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Configuration
public class DataSourceConfig {

    @Bean
    @SuppressWarnings("unchecked")
    CustomDataSource customDataSource(final Environment env) {
        return new CustomDataSource(
                env.getProperty("app.datasource.url"),
                env.getProperty("app.datasource.username"),
                env.getProperty("app.datasource.password"),
                env.getProperty("app.datasource.config.max-connection", Integer.class, Integer.MIN_VALUE),
                env.getProperty("app.datasource.config.timeout", Duration.class),
                env.getProperty("app.datasource.config.options", List.class));
    }

}
