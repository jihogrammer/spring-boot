package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.front_controller.ModelView;
import dev.jihogrammer.member.Members;

import java.util.Map;

public class MemberListController implements Controller {
    private static final String MEMBER_LIST_ATTRIBUTE_NAME = "members";

    private final String viewName;
    private final Members members;

    public MemberListController(final String viewName, final Members members) {
        this.viewName = viewName;
        this.members = members;
    }

    @Override
    public ModelView process(Map<String, String> parametersMap) {
        return new ModelView(viewName).set(MEMBER_LIST_ATTRIBUTE_NAME, members.findAll());
    }
}
