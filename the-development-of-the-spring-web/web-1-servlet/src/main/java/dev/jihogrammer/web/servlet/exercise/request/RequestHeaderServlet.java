package dev.jihogrammer.web.servlet.exercise.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;


@Slf4j
@RequiredArgsConstructor
@WebServlet(urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    private final ObjectMapper objectMapper;

    public RequestHeaderServlet() {
        this.objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Map<String, Map<String, ?>> info = Map.of(
            "protocol", protocol(request),
            "headers", headers(request),
            "utilities", utilities(request),
            "etc", etc(request));
        response.getWriter().write(this.objectMapper.writeValueAsString(info));
    }

    private Map<String, ?> protocol(final HttpServletRequest request) {
        return cleanMap(Map.of(
            "method", Optional.ofNullable(request.getMethod()),
            "protocol", Optional.ofNullable(request.getProtocol()),
            "scheme", Optional.ofNullable(request.getScheme()),
            "url", Optional.ofNullable(request.getRequestURL()),
            "uri", Optional.ofNullable(request.getRequestURI()),
            "query", Optional.ofNullable(request.getQueryString()),
            "secure", Optional.of(request.isSecure())));
    }

    private Map<String, ?> headers(final HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(name -> headers.put(name, request.getHeader(name)));
        return Collections.unmodifiableMap(headers);
    }

    private Map<String, ?> utilities(final HttpServletRequest request) {
        return Map.of(
            "server", cleanMap(Map.of(
                "name", Optional.ofNullable(request.getServerName()),
                "port", Optional.of(request.getServerPort()))),
            "locale", request.getLocales(),
            "cookie", request.getCookies(),
            "content", cleanMap(Map.of(
                "type", Optional.ofNullable(request.getContentType()),
                "encoding", Optional.ofNullable(request.getCharacterEncoding()),
                "length", Optional.of(request.getContentLength()))));
    }

    private Map<String, ?> etc(final HttpServletRequest request) {
        return Map.of(
            "remote", cleanMap(Map.of(
                "host", Optional.ofNullable(request.getRemoteHost()),
                "address", Optional.ofNullable(request.getRemoteAddr()),
                "port", Optional.of(request.getRemotePort()))),
            "local", cleanMap(Map.of(
                "name", Optional.ofNullable(request.getLocalName()),
                "address", Optional.ofNullable(request.getLocalAddr()),
                "port", Optional.of(request.getLocalPort()))));
    }

    private Map<String, ?> cleanMap(final Map<String, Optional<?>> map) {
        return map.entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().get()));
    }
}
