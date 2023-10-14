package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.front_controller.View;
import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberSaveController implements Controller {
    private static final String MEMBER_USERNAME_PARAMETER_NAME = "username";
    private static final String MEMBER_AGE_PARAMETER_NAME = "age";
    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";

    private final View view;
    private final Members members;

    public MemberSaveController(final View view, final Members members) {
        this.view = view;
        this.members = members;
    }

    @Override
    public View process(final HttpServletRequest request, final HttpServletResponse response) {
        String username = request.getParameter(MEMBER_USERNAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        Member member = new Member(username, age);

        Member newMember = members.save(member);
        request.setAttribute(NEW_MEMBER_ATTRIBUTE_NAME, newMember);

        return view;
    }
}
