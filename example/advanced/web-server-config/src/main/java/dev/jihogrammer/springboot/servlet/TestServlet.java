package dev.jihogrammer.springboot.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void service(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        System.out.println("TestServlet#service()");

        response.getWriter().println("Hello, World!");
    }

}
