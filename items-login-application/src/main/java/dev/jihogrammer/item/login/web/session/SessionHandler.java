package dev.jihogrammer.item.login.web.session;

import dev.jihogrammer.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;

import java.util.Optional;

public final class SessionHandler {
    private static final String LOGGED_IN_MEMBER = "logged-in-member";

    public static boolean isAuthMember(@NonNull final HttpServletRequest request) {
        return findMember(request).isPresent();
    }

    public static boolean isNotAuthMember(@NonNull final HttpServletRequest request) {
        return !isAuthMember(request);
    }

    public static void registerMemberSession(@NonNull final HttpServletRequest request, final Member member) {
        request.getSession().setAttribute(LOGGED_IN_MEMBER, member);
    }

    public static Optional<Member> findMember(@NonNull HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false))
            .map(session -> (Member) session.getAttribute(LOGGED_IN_MEMBER));
    }
}
