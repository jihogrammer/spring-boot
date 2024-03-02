package dev.jihogrammer.servlet.mvc.controller.servlet;

import dev.jihogrammer.servlet.mvc.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@WebServlet(urlPatterns = MemberFormServlet.URL)
@Slf4j
public class MemberFormServlet extends HttpServlet {

    public static final String URL = "/servlet/members/new-form";

    private final ViewResolver viewResolver;

    private final String viewName;

    public MemberFormServlet(
        @Value("${page.form}") final String viewName,
        final ViewResolver viewResolver
    ) {
        log.info("viewName = {}", viewName);
        this.viewName = viewName;
        this.viewResolver = viewResolver;
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {}", URL);
        request.getRequestDispatcher(this.viewResolver.resolve(this.viewName)).forward(request, response);
    }
}
