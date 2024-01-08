package dev.jihogrammer.item.login.web.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;

public class SessionManager {
    public static final String MEMBER_COOKIE_NAME = "sessionId";
    private final Map<String, Object> store;

    public SessionManager() {
        this.store = new ConcurrentHashMap<>();
    }

    public void createSession(final Object value, final HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        this.store.put(sessionId, value);
        response.addCookie(new Cookie(MEMBER_COOKIE_NAME, sessionId));
    }

    public Object findByServletRequest(final HttpServletRequest request) {
        return findCookie(request.getCookies(), MEMBER_COOKIE_NAME)
                .map(cookie -> this.store.get(cookie.getValue())).orElse(null);
    }

    public void expire(final HttpServletRequest request) {
        findCookie(request.getCookies(), MEMBER_COOKIE_NAME)
                .ifPresent(cookie -> this.store.remove(cookie.getValue()));
    }

    private Optional<Cookie> findCookie(final Cookie[] cookies, final String cookieName) {
        if (isNull(cookies)) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
            .filter(cookie -> cookie.getName().equals(cookieName))
            .findAny();
    }
}