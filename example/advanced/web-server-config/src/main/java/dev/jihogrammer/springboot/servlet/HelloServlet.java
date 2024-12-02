package dev.jihogrammer.springboot.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void service(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        System.out.println("HelloServlet.service");

        response.getWriter().println("Hello, Servlet!");
    }

}
