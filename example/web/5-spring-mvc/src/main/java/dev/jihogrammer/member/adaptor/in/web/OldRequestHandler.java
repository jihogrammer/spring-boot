package dev.jihogrammer.member.adaptor.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

@Slf4j
@Component("/legacy/http-request-handler")
public class OldRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response
    ) throws IOException {
        response.getWriter().write(this.getClass().getSimpleName());
    }

}
