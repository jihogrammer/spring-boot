package dev.jihogrammer.member.adaptor.in.web.controller;

import dev.jihogrammer.member.adaptor.in.web.model.Controller;

import java.util.Map;

public abstract class ViewNameController implements Controller {

    public abstract String process(Map<String, Object> model);

    @Override
    public String toString() {
        return "username=" + getClass().getSimpleName() + ", uri=" + uri() + ", view=" + view();
    }

}
