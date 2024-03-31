package dev.jihogrammer.gateway.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class TransactionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler
    ) throws Exception {
        MDC.put("transaction.id", UUID.randomUUID().toString());
        return true;
    }
}
