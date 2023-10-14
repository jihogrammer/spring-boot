package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.front_controller.View;
import dev.jihogrammer.member.Members;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListController implements Controller {
    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";

    private final View view;
    private final Members members;

    public MemberListController(final View view, final Members members) {
        this.view = view;
        this.members = members;
    }

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(MEMBER_LIST_ATTRIBUTE_NAME, members.findAll());
        return view;
    }
}
