package dev.jihogrammer.front_controller.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class View {
    private final String viewPath;

    public View(final String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(
            final Map<String, Object> model,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws ServletException, IOException {
        mapModelToRequestAttribute(model, request);
        request.getRequestDispatcher(viewPath).forward(request, response);
    }

    private static void mapModelToRequestAttribute(final Map<String, Object> model, final HttpServletRequest request) {
        model.forEach(request::setAttribute);
    }
}
