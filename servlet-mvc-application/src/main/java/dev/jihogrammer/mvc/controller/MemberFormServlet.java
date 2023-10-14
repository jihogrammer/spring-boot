package dev.jihogrammer.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = MemberFormServlet.URL)
public class MemberFormServlet extends HttpServlet {
    public static final String URL = "/members/new-form";
    private static final String TARGET_JSP_PATH = "/WEB-INF/new-form.jsp";

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(TARGET_JSP_PATH).forward(request, response);
    }
}
