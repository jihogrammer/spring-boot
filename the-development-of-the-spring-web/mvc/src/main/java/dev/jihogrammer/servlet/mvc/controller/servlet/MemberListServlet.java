package dev.jihogrammer.servlet.mvc.controller.servlet;

import dev.jihogrammer.servlet.mvc.ViewResolver;
import dev.jihogrammer.servlet.mvc.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@WebServlet(urlPatterns = MemberListServlet.URL)
@Slf4j
public class MemberListServlet extends HttpServlet {

    public static final String URL = "/servlet/members";

    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";

    private final String viewName;

    private final ViewResolver viewResolver;

    private final MemberService service;

    public MemberListServlet(
        @Value("${page.list}") final String viewName,
        final ViewResolver viewResolver,
        final MemberService service
    ) {
        log.info("viewName = {}", viewName);
        this.viewName = viewName;
        this.viewResolver = viewResolver;
        this.service = service;
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {}", URL);
        request.setAttribute(MEMBER_LIST_ATTRIBUTE_NAME, this.service.findAll());
        request.getRequestDispatcher(this.viewResolver.resolve(this.viewName)).forward(request, response);
    }
}
