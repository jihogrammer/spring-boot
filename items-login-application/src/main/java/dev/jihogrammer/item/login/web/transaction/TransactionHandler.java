package dev.jihogrammer.item.login.web.transaction;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;

import java.util.UUID;

public final class TransactionHandler {
    private static final String START_TIME_MILLIS = "start-time-millis";
    private static final String TRANSACTION_ID = "transaction-id";

    public static void register(@NonNull final HttpServletRequest request) {
        request.setAttribute(START_TIME_MILLIS, System.currentTimeMillis());
        request.setAttribute(TRANSACTION_ID, UUID.randomUUID());
    }

    public static String transactionId(@NonNull final HttpServletRequest request) {
        return request.getAttribute(TRANSACTION_ID).toString();
    }

    public static Object service(@NonNull final HttpServletRequest request) {
        return new Object() {
            @Override
            public String toString() {
                return request.getMethod() + " " + request.getRequestURI();
            }
        };
    }

    public static long elapsed(@NonNull final HttpServletRequest request) {
        return System.currentTimeMillis() - ((long) request.getAttribute(START_TIME_MILLIS));
    }
}
