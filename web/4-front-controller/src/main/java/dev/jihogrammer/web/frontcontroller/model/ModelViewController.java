package dev.jihogrammer.web.frontcontroller.model;

import jakarta.servlet.http.HttpServletRequest;

public abstract class ModelViewController implements Controller {

    public abstract ModelView process(HttpServletRequest request);

    @Override
    public String toString() {
        return "name=" + getClass().getSimpleName() + ", uri=" + uri() + ", view=" + view();
    }

}
