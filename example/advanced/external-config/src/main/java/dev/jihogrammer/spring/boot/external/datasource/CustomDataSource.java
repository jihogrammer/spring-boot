package dev.jihogrammer.spring.boot.external.datasource;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class CustomDataSource {

    private String url;

    private String username;

    private String password;

    private int maxConnection;

    private Duration timeout;

    private List<String> options;

    @PostConstruct
    private void postConstruct() {
        log.info("{} url={}", this.getClass().getSimpleName(), this.url);
        log.info("{} username={}", this.getClass().getSimpleName(), this.username);
        log.info("{} password={}", this.getClass().getSimpleName(), this.password);
        log.info("{} maxConnection={}", this.getClass().getSimpleName(), this.maxConnection);
        log.info("{} timeout={}", this.getClass().getSimpleName(), this.timeout);
        log.info("{} options={}", this.getClass().getSimpleName(), this.options);
    }

}
