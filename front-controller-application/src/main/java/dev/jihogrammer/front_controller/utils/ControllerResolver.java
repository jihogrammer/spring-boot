package dev.jihogrammer.front_controller.utils;

import dev.jihogrammer.front_controller.model.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class ControllerResolver {

    private final Map<String, Controller> controllerMap;

    public Controller resolve(final String uri, final HttpServletResponse response) throws ServletException {
        return Optional.ofNullable(controllerMap.get(uri))
                .orElseThrow(() -> {
                    log.error("NOT FOUND uri={}", uri);
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return new ServletException("404 Not Found.");
                });
    }

}
