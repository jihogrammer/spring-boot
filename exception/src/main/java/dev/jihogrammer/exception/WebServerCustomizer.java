package dev.jihogrammer.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(final ConfigurableWebServerFactory factory) {
        factory.addErrorPages(
            new ErrorPage(HttpStatus.NOT_FOUND, ErrorPagePath.NOT_FOUND),
            new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorPagePath.INTERNAL_SERVER_ERROR),
            new ErrorPage(RuntimeException.class, ErrorPagePath.INTERNAL_SERVER_ERROR));
    }
}
