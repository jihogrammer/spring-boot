package dev.jihogrammer.spring.exception.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ElapsedLoggingInterceptor implements HandlerInterceptor {
    private static final String START_TIME_MILLIS = "start-time-millis";

    @Override
    public boolean preHandle(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler
    ) {
        request.setAttribute(START_TIME_MILLIS, System.currentTimeMillis());
        log.info("INTERCEPTOR [{}]", request.getDispatcherType());
        return true;
    }

    @Override
    public void afterCompletion(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler,
        final Exception ex
    ) {
        log.info("elapsed: {} ms", System.currentTimeMillis() - ((long) request.getAttribute(START_TIME_MILLIS)));
    }
}
