package dev.jihogrammer.web.core.transaction;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
public class TransactionLoggingInterceptor implements HandlerInterceptor {

    private final String transactionHeaderName;

    private final String transactionKey;

    @Override
    public boolean preHandle(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler
    ) {
        String transactionId = request.getHeader(this.transactionHeaderName);

        if (isNull(transactionId) || transactionId.isBlank()) {
            MDC.put(this.transactionKey, UUID.randomUUID().toString());
        } else {
            MDC.put(this.transactionKey, transactionId);
        }

        return true;
    }

}
