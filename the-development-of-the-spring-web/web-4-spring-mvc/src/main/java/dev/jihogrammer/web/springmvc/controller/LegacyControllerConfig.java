package dev.jihogrammer.web.springmvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.IOException;
import java.util.Map;

@Configuration
public class LegacyControllerConfig {

    @Slf4j
    @Component(URI.LEGACY_CONTROLLER_HOME)
    public static class HomeController implements Controller {

        private final String viewName;

        public HomeController(@Value("${service.legacy.home.get-view-name}") final String viewName) {
            this.viewName = viewName;
        }

        @Override
        public ModelAndView handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
        ) {
            log.info("REQUEST LEGACY HOME");

            var model = Map.of("controller", this.getClass().getSimpleName());
            return new ModelAndView(this.viewName, model);
        }
    }

    @Slf4j
    @Component(URI.LEGACY_REQUEST_HANDLER)
    public static class LegacyRequestHandler implements HttpRequestHandler {

        @Override
        public void handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
        ) throws ServletException, IOException {
            log.info("REQUEST LEGACY HTTP REQUEST HANDLER");

            response.getWriter()
                    .write(">>> " + this.getClass().getSimpleName());
        }
    }

}
