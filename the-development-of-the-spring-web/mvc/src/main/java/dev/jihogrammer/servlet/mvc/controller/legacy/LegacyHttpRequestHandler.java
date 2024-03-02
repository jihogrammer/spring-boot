package dev.jihogrammer.servlet.mvc.controller.legacy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

@Slf4j
@Component(value = LegacyHttpRequestHandler.URI)
public class LegacyHttpRequestHandler implements HttpRequestHandler {

    public static final String URI = "/legacy/http-request-handler";

    @Override
    public void handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
    ) throws IOException {
        log.info("REQUEST HTTP REQUEST HANDLER - {}", request);
        response.getWriter().write(">>> " + this.getClass().getSimpleName());
    }

}
