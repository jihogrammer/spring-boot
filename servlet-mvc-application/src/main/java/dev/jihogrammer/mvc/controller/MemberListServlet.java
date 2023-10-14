package dev.jihogrammer.mvc.controller;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;
import dev.jihogrammer.mvc.repository.SingletonInMemoryMembers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = MemberListServlet.URL)
public class MemberListServlet extends HttpServlet {
    public static final String URL = "/members";

    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";
    private static final String TARGET_JSP_PATH = "/WEB-INF/list.jsp";

    private final Members members = SingletonInMemoryMembers.getInstance();

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MEMBER_LIST_ATTRIBUTE_NAME, members.findAll());
        request.getRequestDispatcher(TARGET_JSP_PATH).forward(request, response);
    }
}
