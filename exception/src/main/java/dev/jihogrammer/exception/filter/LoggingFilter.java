package dev.jihogrammer.exception.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain
    ) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();

        try {
            log.info("REQ [{}][{}][{}]", uuid, request.getDispatcherType(), ((HttpServletRequest) request).getRequestURI());
            chain.doFilter(request, response);
        } finally {
            log.info("RES [{}][{}][{}]", uuid, request.getDispatcherType(), ((HttpServletRequest) request).getRequestURI());
        }
    }
}
