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

@WebServlet(urlPatterns = MemberSaveServlet.URL)
@Slf4j
public class MemberSaveServlet extends HttpServlet {

    public static final String URL = "/servlet/members/save";

    private static final String MEMBER_NAME_PARAMETER_NAME = "name";

    private static final String MEMBER_AGE_PARAMETER_NAME = "age";

    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";

    private final String viewName;

    private final ViewResolver viewResolver;

    private final MemberService service;

    public MemberSaveServlet(
        @Value("${page.save}") final String viewName,
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
        String name = request.getParameter(MEMBER_NAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        log.info("REQUEST {}, name=[{}], age=[{}]", URL, name, age);

        request.setAttribute(NEW_MEMBER_ATTRIBUTE_NAME, this.service.save(name, age));
        request.getRequestDispatcher(this.viewResolver.resolve(this.viewName)).forward(request, response);
    }
}
