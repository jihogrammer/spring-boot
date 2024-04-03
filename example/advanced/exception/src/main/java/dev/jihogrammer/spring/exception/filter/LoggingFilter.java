package dev.jihogrammer.spring.exception.filter;

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
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        log.info("[{} {}] dispatcherType=[{}]",
            httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), request.getDispatcherType());

        chain.doFilter(request, response);
    }

}
