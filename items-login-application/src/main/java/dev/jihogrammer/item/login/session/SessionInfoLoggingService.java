package dev.jihogrammer.item.login.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class SessionInfoLoggingService {
    public void logSessionInfo(final HttpSession httpSession) {
        if (isNull(httpSession)) {
            return;
        }

        logSessionAttributes(httpSession);
    }

    private void logSessionAttributes(final HttpSession httpSession) {
        httpSession.getAttributeNames().asIterator()
            .forEachRemaining(name -> log.info("session info '{}': {}", name, httpSession.getAttribute(name)));

        log.info("session id: {}", httpSession.getId());
        log.info("session MaxInactiveInterval: {}", httpSession.getMaxInactiveInterval());
        log.info("session CreationTime: {}", LocalDateTime.ofInstant(Instant.ofEpochMilli(httpSession.getCreationTime()), ZoneId.systemDefault()));
        log.info("session LastAccessedTime: {}", LocalDateTime.ofInstant(Instant.ofEpochMilli(httpSession.getLastAccessedTime()), ZoneId.systemDefault()));
        log.info("session isNew: {}", httpSession.isNew());
    }
}
