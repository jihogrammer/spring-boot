package dev.jihogrammer.spring_mvc.controller;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;
import dev.jihogrammer.spring_mvc.repository.SingletonInMemoryMembers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberSaveController {
    private static final String URI = "/members/save";
    private static final String TARGET_VIEW_NAME = "save-result";
    private static final String MEMBER_USERNAME_PARAMETER_NAME = "username";
    private static final String MEMBER_AGE_PARAMETER_NAME = "age";
    private static final String MEMBER_ATTRIBUTE_NAME = "newMember";

    private final Members members;

    public MemberSaveController() {
        this.members = SingletonInMemoryMembers.getInstance();
    }

    @RequestMapping(URI)
    public ModelAndView save(final HttpServletRequest request, final HttpServletResponse response) {
        String username = request.getParameter(MEMBER_USERNAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        Member member = new Member(username, age);

        Member newMember = members.save(member);

        ModelAndView modelAndView = new ModelAndView(TARGET_VIEW_NAME);
        modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, newMember);

        return modelAndView;
    }
}
