package dev.jihogrammer.web.frontcontroller.model;

import java.util.Map;

public abstract class ViewNameController implements Controller {

    public abstract String process(Map<String, Object> model);

    @Override
    public String toString() {
        return "name=" + getClass().getSimpleName() + ", uri=" + uri() + ", view=" + view();
    }

}
