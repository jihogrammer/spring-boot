package dev.jihogrammer.front_controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class View {
    private final String viewPath;

    public View(final String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
