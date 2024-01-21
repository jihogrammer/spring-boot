package dev.jihogrammer.item.login.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

import static dev.jihogrammer.item.login.web.session.SessionChecker.isNotAuthMember;

@Slf4j
public class SignInCheckFilter implements Filter {
    private static final String[] WHITELIST = {"/", "/sign-in", "/sign-up", "*.css"};

    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain
    ) throws IOException, ServletException {
        try {
            String requestURI = ((HttpServletRequest) request).getRequestURI();
            if (shouldCheckMember(requestURI)) {
                log.info("start check member");
                HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
                if (isNotAuthMember(httpSession)) {
                    ((HttpServletResponse) response).sendRedirect("/sign-in?redirectUri=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } finally {
            log.info("end check member");
        }
    }

    private boolean shouldCheckMember(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITELIST, requestURI);
    }
}
