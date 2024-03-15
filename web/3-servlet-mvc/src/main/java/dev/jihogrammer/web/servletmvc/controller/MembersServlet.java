package dev.jihogrammer.web.servletmvc.controller;

import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.web.servletmvc.ServletMVCApplication;
import dev.jihogrammer.web.servletmvc.model.web.response.MemberView;
import dev.jihogrammer.web.servletmvc.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
@WebServlet(urlPatterns = MembersServlet.URL)
public class MembersServlet extends HttpServlet {

    public static final String URL = "/members";

    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";

    /**
     * @see ServletMVCApplication#membersViewResolver
     */
    private final ViewResolver membersViewResolver;

    /**
     * @see ServletMVCApplication#members
     */
    private final Members members;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {} {}", request.getMethod(), URL);

        var foundMembers = MemberView.of(this.members.findAll());
        log.info("found members {}", foundMembers);

        request.setAttribute(MEMBER_LIST_ATTRIBUTE_NAME, foundMembers);
        request.getRequestDispatcher(this.membersViewResolver.resolveGetView()).forward(request, response);
    }

}
