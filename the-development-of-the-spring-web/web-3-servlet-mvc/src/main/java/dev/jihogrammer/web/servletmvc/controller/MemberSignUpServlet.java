package dev.jihogrammer.web.servletmvc.controller;

import dev.jihogrammer.web.servletmvc.service.MemberService;
import dev.jihogrammer.web.servletmvc.utils.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = MemberSignUpServlet.URL)
public class MemberSignUpServlet extends HttpServlet {

    public static final String URL = "/members/sign-up";

    private static final String MEMBER_NAME_PARAMETER_NAME = "name";

    private static final String MEMBER_AGE_PARAMETER_NAME = "age";

    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";

    private final ViewResolver viewResolver;

    private final String getViewName;

    private final String postViewName;

    private final MemberService service;

    public MemberSignUpServlet(
        @Value("${service.sign-up.get-view}") final String getViewName,
        @Value("${service.sign-up.post-view}") final String postViewName,
        final ViewResolver viewResolver,
        final MemberService service
    ) {
        log.info("getViewName = {}, postViewName = {}", getViewName, postViewName);
        this.getViewName = getViewName;
        this.postViewName = postViewName;
        this.viewResolver = viewResolver;
        this.service = service;
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {}", URL);
        request.getRequestDispatcher(this.viewResolver.resolve(this.getViewName)).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(MEMBER_NAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        log.info("REQUEST {}, name=[{}], age=[{}]", URL, name, age);

        request.setAttribute(NEW_MEMBER_ATTRIBUTE_NAME, this.service.save(name, age));
        request.getRequestDispatcher(this.viewResolver.resolve(this.postViewName)).forward(request, response);
    }

}
