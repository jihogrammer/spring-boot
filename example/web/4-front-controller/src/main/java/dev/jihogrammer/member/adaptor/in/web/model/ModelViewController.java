package dev.jihogrammer.member.adaptor.in.web.model;

import dev.jihogrammer.member.adaptor.in.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;

public abstract class ModelViewController implements Controller {

    public abstract ModelView process(HttpServletRequest request);

    @Override
    public String toString() {
        return "username=" + getClass().getSimpleName() + ", uri=" + uri() + ", view=" + view();
    }

}
