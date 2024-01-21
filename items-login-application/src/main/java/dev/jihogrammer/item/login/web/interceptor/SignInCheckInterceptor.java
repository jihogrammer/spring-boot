package dev.jihogrammer.item.login.web.interceptor;

import dev.jihogrammer.item.login.web.session.SessionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

import static dev.jihogrammer.item.login.web.transaction.TransactionHandler.service;
import static dev.jihogrammer.item.login.web.transaction.TransactionHandler.transactionId;

@Slf4j
public class SignInCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final Object handler
    ) throws IOException {
        if (SessionHandler.isNotAuthMember(request)) {
            log.info("[{}][{}] it is not auth transaction", transactionId(request), service(request));
            response.sendRedirect("/sign-in?redirectUri=" + request.getRequestURI());
            return false;
        }
        return true;
    }
}
