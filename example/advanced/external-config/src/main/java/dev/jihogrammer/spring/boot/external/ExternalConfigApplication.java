package dev.jihogrammer.spring.boot.external;

import dev.jihogrammer.spring.boot.external.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "dev.jihogrammer.spring.boot.external.datasource")
@Import(DataSourceConfig.class)
public class ExternalConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExternalConfigApplication.class, args);
    }

}
