package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.model.ViewNameController;

import java.util.Map;

public class MemberFormController implements ViewNameController {
    private final String viewName;

    public MemberFormController(final String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String process(Map<String, String> parametersMap, Map<String, Object> model) {
        return viewName;
    }
}
