package dev.jihogrammer.exercise.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


@WebServlet(urlPatterns = "/request-header")
@Slf4j
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        printRequestProtocol(request);
        printRequestHeaders(request);
        printRequestUtilities(request);
        printETC(request);
    }

    private void printRequestProtocol(final HttpServletRequest request) {
        log.info("--- REQUEST PROTOCOL ---");
        log.info("method: {}", request.getMethod());
        log.info("protocol: {}", request.getProtocol());
        log.info("scheme: {}", request.getScheme());
        log.info("url: {}", request.getRequestURL());
        log.info("uri: {}", request.getRequestURI());
        log.info("query string: {}", request.getQueryString());
        log.info("secure: {}", request.isSecure());
    }

    private void printRequestHeaders(final HttpServletRequest request) {
        log.info("--- REQUEST HEADERS ---");
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(name -> log.info("{}: {}", name, request.getHeader(name)));
    }

    private void printRequestUtilities(final HttpServletRequest request) {
        log.info("--- REQUEST UTILITIES ---");
        log.info("------ HOST");
        log.info("server name: {}", request.getServerName());
        log.info("server port: {}", request.getServerPort());
        log.info("------ ACCEPT LANGUAGE");
        request.getLocales().asIterator().forEachRemaining(locale -> log.info("locale: {}", locale));
        log.info("------ COOKIES");
        Optional.ofNullable(request.getCookies())
                .ifPresent(cookies -> Stream.of(cookies)
                        .forEach(cookie -> log.info("{}: {}", cookie.getName(), cookie.getValue())));
        log.info("------ CONTENT");
        log.info("content type: {}", request.getContentType());
        log.info("content encoding: {}", request.getCharacterEncoding());
        log.info("content length: {}", request.getContentLength());
    }

    private void printETC(final HttpServletRequest request) {
        log.info("--- REQUEST ETC ---");
        log.info("------ REMOTE");
        log.info("remote host: {}", request.getRemoteHost());
        log.info("remote address: {}", request.getRemoteAddr());
        log.info("remote port: {}", request.getRemotePort());
        log.info("------ LOCAL");
        log.info("local name: {}", request.getLocalName());
        log.info("local address: {}", request.getLocalAddr());
        log.info("local port: {}", request.getLocalPort());
    }
}
