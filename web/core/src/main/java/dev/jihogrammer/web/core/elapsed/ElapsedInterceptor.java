package dev.jihogrammer.web.core.elapsed;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ElapsedInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> START_TIME = ThreadLocal.withInitial(() -> 0L);

    @Override
    public boolean preHandle(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler
    ) {
        START_TIME.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler,
            Exception ex
    ) {
        var elapsed = System.currentTimeMillis() - START_TIME.get();
        MDC.put("elapsed", Long.toString(elapsed));
        log.trace("[{} {}] elapsed {} ms.", request.getMethod(), request.getRequestURI(), elapsed);
    }

}
