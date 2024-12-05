package dev.jihogrammer.spring.boot.external;

import dev.jihogrammer.spring.boot.external.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "dev.jihogrammer.spring.boot.external.datasource")
//@Import(DataSourceEnvConfig.class)
//@Import(DataSourceValueConfig.class)
//@Import(V1DataSourcePropertiesConfig.class) @ConfigurationPropertiesScan
//@Import(V2DataSourcePropertiesConfig.class)
@Import(V3DataSourcePropertiesConfig.class)
public class ExternalConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExternalConfigApplication.class, args);
    }

}
