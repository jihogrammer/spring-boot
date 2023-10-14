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

@WebServlet(urlPatterns = MemberSaveServlet.URL)
public class MemberSaveServlet extends HttpServlet {
    public static final String URL = "/members/save";

    private static final String MEMBER_USERNAME_PARAMETER_NAME = "username";
    private static final String MEMBER_AGE_PARAMETER_NAME = "age";
    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";
    private static final String TARGET_JSP_PATH = "/WEB-INF/save-result.jsp";

    private final Members members = SingletonInMemoryMembers.getInstance();

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(MEMBER_USERNAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        Member member = new Member(username, age);

        Member newMember = members.save(member);
        request.setAttribute(NEW_MEMBER_ATTRIBUTE_NAME, newMember);

        request.getRequestDispatcher(TARGET_JSP_PATH).forward(request, response);
    }
}
