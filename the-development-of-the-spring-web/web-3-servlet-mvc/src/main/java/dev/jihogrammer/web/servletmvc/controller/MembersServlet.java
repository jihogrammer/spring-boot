package dev.jihogrammer.web.servletmvc.controller;

import dev.jihogrammer.web.servletmvc.utils.ViewResolver;
import dev.jihogrammer.web.servletmvc.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = MembersServlet.URL)
public class MembersServlet extends HttpServlet {

    public static final String URL = "/members";

    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";

    private final String viewName;

    private final ViewResolver viewResolver;

    private final MemberService service;

    public MembersServlet(
        @Value("${service.members.view}") final String viewName,
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
