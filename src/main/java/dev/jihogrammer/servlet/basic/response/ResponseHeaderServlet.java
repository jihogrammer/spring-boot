package dev.jihogrammer.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(urlPatterns = "/response-header-servlet")
@Slf4j
public class ResponseHeaderServlet extends HttpServlet {
    public static final String CUSTOM_HEADER_KEY = "Custom-Header";
    public static final String CUSTOM_COOKIE_NAME = "Custom-Cookie";
    public static final String CUSTOM_COOKIE_VALUE = "fresh-berry";

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> headers = new HashMap<>();
        request.getHeaderNames().asIterator().forEachRemaining(name -> headers.put(name, request.getHeader(name)));
        String body = StreamUtils.copyToString(request.getInputStream(), UTF_8);
        log.info("request in: headers={}, body={}", headers, body);

        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);
        // [response-header]
        response.setHeader("Cookie-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader(CUSTOM_HEADER_KEY, request.getHeader(CUSTOM_HEADER_KEY));
        setContentType(response);
        setCookie(response);
        setRedirect(response);
    }

    private void setCookie(final HttpServletResponse response) {
        // [response-header-cookie]
        // == response.setHeader("Set-Cookie", "${CUSTOM_COOKIE_NAME}=${CUSTOM_COOKIE_VALUE}; Max-Age=600");
        Cookie cookie = new Cookie(CUSTOM_COOKIE_NAME, CUSTOM_COOKIE_VALUE);
        cookie.setMaxAge(10 * 60);
        response.addCookie(cookie);
    }

    private void setContentType(final HttpServletResponse response) {
        // [response-header-utils]
        // == response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void setRedirect(final HttpServletResponse response) throws IOException {
        // [response-redirect]
        // == response.setStatus(SC_FOUND); response.setHeader("Location", "/");
        response.sendRedirect("/");
    }
}
