package dev.jihogrammer.web.session.port.in;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class Session<T> {

    private final String name;

    public Session(final String name) {
        this.name = name;
    }

    @SuppressWarnings("unchecked")
    public Optional<T> fetch() {
        return this.httpSession(false)
                .map(httpSession -> (T) httpSession.getAttribute(this.name));
    }

    public void flush(final T attribute) {
        this.httpSession(true)
                .ifPresent(httpSession -> httpSession.setAttribute(this.name, attribute));
    }

    public boolean isAuthed() {
        return this.httpSession(false)
                .map(httpSession -> httpSession.getAttribute(this.name))
                .isPresent();
    }

    public boolean isNotAuthed() {
        return !this.isAuthed();
    }

    public void invalidate() {
        this.httpSession(false).ifPresent(HttpSession::invalidate);
    }

    private Optional<HttpSession> httpSession(final boolean create) {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getSession(create));
    }

}
