package dev.jihogrammer.web.core.transaction;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
public class TransactionLoggingFilter implements Filter {

    private final String txHeaderName;

    private final String txMDCKey;

    @Override
    public void init(FilterConfig filterConfig) {
        log.trace("{} initialized; txHeaderName=[{}]; txMDCKey=[{}];",
            getClass().getSimpleName(), this.txHeaderName, this.txMDCKey);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String transactionId = httpServletRequest.getHeader(this.txHeaderName);

        if (isNull(transactionId) || transactionId.isBlank()) {
            MDC.put(this.txMDCKey, UUID.randomUUID().toString());
        } else {
            MDC.put(this.txMDCKey, transactionId);
        }

        ((HttpServletResponse) response).setHeader(this.txHeaderName, MDC.get(this.txMDCKey));

        try {
            log.info("[{} {}] IN", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
            chain.doFilter(request, response);
        } finally {
            log.info("[{} {}] OUT", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        }
    }

}
