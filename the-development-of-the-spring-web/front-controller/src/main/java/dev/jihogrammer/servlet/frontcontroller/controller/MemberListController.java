package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.model.ViewNameController;
import dev.jihogrammer.front_controller.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberListController implements ViewNameController {

    private final String uri;

    private final String viewName;

    private final MemberService service;

    @Override
    public String uri() {
        return this.uri;
    }

    @Override
    public String view() {
        return this.viewName;
    }

    @Override
    public String process(final Map<String, Object> model) {
        this.service.fetchMembers(model);
        return this.viewName;
    }
}
