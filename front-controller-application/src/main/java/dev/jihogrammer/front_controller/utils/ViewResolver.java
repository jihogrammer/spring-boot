package dev.jihogrammer.front_controller.utils;

import dev.jihogrammer.front_controller.model.View;

public class ViewResolver {
    private final String viewPathPrefix;
    private final String viewPathSuffix;

    public ViewResolver(final String viewPathPrefix, final String viewPathSuffix) {
        this.viewPathPrefix = viewPathPrefix;
        this.viewPathSuffix = viewPathSuffix;
    }

    public View resolve(final String viewName) {
        return new View(viewPathPrefix + viewName + viewPathSuffix);
    }
}
