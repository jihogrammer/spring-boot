package dev.jihogrammer.spring.boot.external.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("app.datasource")
public class V1CustomDataSourceProperties {

    private String url;

    private String username;

    private String password;

    private Config config;

    @Data
    public static class Config {

        private int maxConnection;

        private Duration timeout;

        private List<String> options;

    }

}
