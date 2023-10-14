package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.member.Members;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberListController implements Controller {
    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";
    private static final String TARGET_JSP_PATH = "/WEB-INF/list.jsp";

    private final Members members;

    public MemberListController(final Members members) {
        this.members = members;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MEMBER_LIST_ATTRIBUTE_NAME, members.findAll());
        request.getRequestDispatcher(TARGET_JSP_PATH).forward(request, response);
    }
}
