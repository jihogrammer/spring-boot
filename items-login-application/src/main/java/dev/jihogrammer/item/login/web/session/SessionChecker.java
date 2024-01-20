package dev.jihogrammer.item.login.web.session;

import jakarta.servlet.http.HttpSession;

import static dev.jihogrammer.item.login.web.SessionConstant.LOGGED_IN_MEMBER;
import static java.util.Objects.nonNull;

public final class SessionChecker {
    public static boolean isAuthMember(final HttpSession httpSession) {
        return nonNull(httpSession) && nonNull(httpSession.getAttribute(LOGGED_IN_MEMBER));
    }

    public static boolean isNotAuthMember(final HttpSession httpSession) {
        return !isAuthMember(httpSession);
    }
}
