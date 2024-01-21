package dev.jihogrammer.item.login.web.interceptor;

import dev.jihogrammer.item.login.web.transaction.TransactionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static dev.jihogrammer.item.login.web.transaction.TransactionHandler.*;
import static java.util.Objects.nonNull;

@Slf4j
public class TransactionLoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler
    ) {
        TransactionHandler.register(request);
        log.info("[{}][{}] REQUEST IN - {}", transactionId(request), service(request), handler);
        return true;
    }

    @Override
    public void postHandle(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler,
        final ModelAndView modelAndView
    ) {
        if (nonNull(modelAndView)) {
            log.info("[{}][{}] ModelAndView - {}", transactionId(request), service(request), modelAndView);
        }
    }

    @Override
    public void afterCompletion(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler,
        Exception ex
    ) {
        if (nonNull(ex)) {
            log.error("[{}][{}] RESPONSE ERROR - {}", transactionId(request), service(request), handler, ex);
        }
        log.info("[{}][{}][{} ms] RESPONSE OUT - {}", transactionId(request), service(request), elapsed(request), handler);
    }
}
