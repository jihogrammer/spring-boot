package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormController implements Controller {
    private static final String TARGET_JSP_PATH = "/WEB-INF/new-form.jsp";

    @Override
    public void process(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher(TARGET_JSP_PATH).forward(request, response);
    }
}
