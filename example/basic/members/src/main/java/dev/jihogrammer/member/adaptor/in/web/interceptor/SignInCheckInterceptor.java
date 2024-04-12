package dev.jihogrammer.member.adaptor.in.web.interceptor;

import dev.jihogrammer.member.adaptor.in.web.entity.SignedInMember;
import dev.jihogrammer.web.core.session.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class SignInCheckInterceptor implements HandlerInterceptor {

    private final Session<SignedInMember> memberSession;

    @Override
    public boolean preHandle(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler
    ) throws Exception {
        if (this.memberSession.isNotAuthed()) {
            log.debug("This session is not authed.");
            response.sendRedirect("/members/sign-in?redirectURI=" + request.getRequestURI());
            return false;
        }
        return true;
    }

}
