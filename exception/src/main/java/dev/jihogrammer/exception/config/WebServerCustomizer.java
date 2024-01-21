package dev.jihogrammer.exception.config;

import dev.jihogrammer.exception.error.ErrorPageController;
import dev.jihogrammer.exception.servlet.ServletExceptionController;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;

/**
 * Error Page View HTML 파일 경로를 입력하는 게 아니라, Controller 경로를 입력하는 것이다.
 * @see ServletExceptionController
 * @see ErrorPageController
 */
// @Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(final ConfigurableWebServerFactory factory) {
        factory.addErrorPages(
            new ErrorPage(HttpStatus.NOT_FOUND, ErrorPageController.ERROR_URI_404),
            new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorPageController.ERROR_URI_500),
            new ErrorPage(RuntimeException.class, ErrorPageController.ERROR_URI_500));
    }
}
