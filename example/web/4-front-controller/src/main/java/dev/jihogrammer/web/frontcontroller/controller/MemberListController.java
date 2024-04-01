package dev.jihogrammer.web.frontcontroller.controller;

import dev.jihogrammer.web.frontcontroller.model.ViewNameController;
import dev.jihogrammer.web.frontcontroller.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberListController extends ViewNameController {

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
