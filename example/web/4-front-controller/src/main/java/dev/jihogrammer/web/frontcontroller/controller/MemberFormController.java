package dev.jihogrammer.web.frontcontroller.controller;

import dev.jihogrammer.web.frontcontroller.model.ViewNameController;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberFormController extends ViewNameController {

    private final String uri;

    private final String viewName;

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
        return viewName;
    }

}
