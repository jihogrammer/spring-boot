package dev.jihogrammer.front_controller.model;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private final String viewName;
    private final Map<String, Object> model;

    public ModelView(final String viewName, final Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String viewName() {
        return viewName;
    }

    public Map<String, Object> model() {
        return model;
    }

    public ModelView set(final String key, final Object value) {
        model.put(key, value);
        return this;
    }
}
