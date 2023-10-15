package dev.jihogrammer.spring_mvc.legacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

@Component(value = "/legacy/http-request-handler")
public class LegacyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
    ) throws ServletException, IOException {
        response.getWriter().write(this.getClass().getSimpleName());
    }
}
