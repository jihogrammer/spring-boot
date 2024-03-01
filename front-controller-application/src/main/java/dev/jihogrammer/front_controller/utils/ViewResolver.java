package dev.jihogrammer.front_controller.utils;

import dev.jihogrammer.front_controller.model.View;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ViewResolver {

    private final String viewPathPrefix;

    private final String viewPathSuffix;

    public View resolve(final String viewName) {
        String viewPath = this.viewPathPrefix + viewName + this.viewPathSuffix;
        log.debug("resolve view path: {}", viewPath);
        return new View(viewPath);
    }
}
