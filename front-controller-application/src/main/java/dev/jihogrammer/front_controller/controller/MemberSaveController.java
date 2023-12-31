package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.model.ModelView;
import dev.jihogrammer.front_controller.model.ModelViewController;
import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberSaveController implements ModelViewController {
    private static final String MEMBER_USERNAME_PARAMETER_NAME = "username";
    private static final String MEMBER_AGE_PARAMETER_NAME = "age";
    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";

    private final String viewName;
    private final Members members;

    public MemberSaveController(final String viewName, final Members members) {
        this.viewName = viewName;
        this.members = members;
    }

    @Override
    public ModelView process(Map<String, String> parametersMap) {
        String username = parametersMap.get(MEMBER_USERNAME_PARAMETER_NAME);
        int age = Integer.parseInt(parametersMap.get(MEMBER_AGE_PARAMETER_NAME));
        Member member = new Member(username, age);

        Member newMember = members.save(member);
        Map<String, Object> model = new HashMap<>();
        model.put(NEW_MEMBER_ATTRIBUTE_NAME, newMember);

        return new ModelView(viewName, model);
    }
}
