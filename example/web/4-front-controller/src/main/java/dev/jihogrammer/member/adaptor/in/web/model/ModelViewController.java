package dev.jihogrammer.member.adaptor.in.web.controller;

import dev.jihogrammer.member.adaptor.in.web.model.Controller;
import dev.jihogrammer.member.adaptor.in.web.entity.ModelView;
import jakarta.servlet.http.HttpServletRequest;

public abstract class ModelViewController implements Controller {

    public abstract ModelView process(HttpServletRequest request);

    @Override
    public String toString() {
        return "username=" + getClass().getSimpleName() + ", uri=" + uri() + ", view=" + view();
    }

}
