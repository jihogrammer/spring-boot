package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.front_controller.ModelView;

import java.util.Map;

public class MemberFormController implements Controller {
    private final ModelView modelView;

    public MemberFormController(final String viewName) {
        this.modelView = new ModelView(viewName);
    }

    @Override
    public ModelView process(Map<String, String> parametersMap) {
        return modelView;
    }
}
