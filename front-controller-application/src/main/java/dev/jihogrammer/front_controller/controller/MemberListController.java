package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.model.ViewNameController;
import dev.jihogrammer.member.port.out.Members;

import java.util.Map;

public class MemberListController implements ViewNameController {
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
