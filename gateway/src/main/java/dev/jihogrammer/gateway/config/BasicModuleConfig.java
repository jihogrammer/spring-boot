package dev.jihogrammer.gateway.config;

import dev.jihogrammer.gateway.util.ControllerUriCollector;
import dev.jihogrammer.spring.basic.http.HttpApplication;
import dev.jihogrammer.spring.basic.thymeleaf.ThymeleafApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Collection;

@Configuration
@ComponentScan(
        basePackageClasses = {
                HttpApplication.class,
                ThymeleafApplication.class
        }
)
@Slf4j
public class BasicModuleConfig {

    @Bean
    public Collection<String> basicModuleUris() throws IOException {
        var uris = ControllerUriCollector.collectUris(HttpApplication.class, RequestMethod.GET);

        log.info("Registered basic module uris: {}", uris);

        return uris;
    }

}
