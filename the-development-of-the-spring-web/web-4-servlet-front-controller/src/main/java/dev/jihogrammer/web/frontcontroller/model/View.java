package dev.jihogrammer.web.frontcontroller.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class View {

    private final String viewPath;

    public void render(
            final Map<String, Object> model,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws ServletException, IOException {
        model.entrySet().stream()
                .peek(entry -> log.debug("{}={}", entry.getKey(), entry.getValue()))
                .forEach(entry -> request.setAttribute(entry.getKey(), entry.getValue()));
        request.getRequestDispatcher(viewPath).forward(request, response);
    }

}
