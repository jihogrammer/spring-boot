package dev.jihogrammer.item.login.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class TransactionElapsedLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("TransactionElapsedLoggingFilter is registered.");
    }

    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain
    ) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        try {
            chain.doFilter(request, response);
        } finally {
            log.info(
                "Transaction [{}][{}] elapsed {} ms",
                UUID.randomUUID(),
                ((HttpServletRequest) request).getRequestURI(),
                System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void destroy() {
        log.info("TransactionElapsedLoggingFilter is destroyed.");
    }
}
