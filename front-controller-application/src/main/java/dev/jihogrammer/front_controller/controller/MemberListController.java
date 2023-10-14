package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
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
    public String process(Map<String, String> parametersMap, Map<String, Object> model) {
        model.put(MEMBER_LIST_ATTRIBUTE_NAME, members.findAll());
        return viewName;
    }
}
