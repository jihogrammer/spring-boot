package dev.jihogrammer.spring.boot.external;

import dev.jihogrammer.spring.boot.external.config.DataSourceEnvConfig;
import dev.jihogrammer.spring.boot.external.config.DataSourceV1PropertiesConfig;
import dev.jihogrammer.spring.boot.external.config.DataSourceValueConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "dev.jihogrammer.spring.boot.external.datasource")
//@Import(DataSourceEnvConfig.class)
//@Import(DataSourceValueConfig.class)
@Import(DataSourceV1PropertiesConfig.class)
@ConfigurationPropertiesScan
public class ExternalConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExternalConfigApplication.class, args);
    }

}
