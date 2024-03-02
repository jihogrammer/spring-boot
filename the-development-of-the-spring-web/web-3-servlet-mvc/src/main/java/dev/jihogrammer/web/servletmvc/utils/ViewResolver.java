package dev.jihogrammer.web.servletmvc.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ViewResolver {

    private final String prefix;

    private final String suffix;

    public String resolve(final String viewName) {
        var resolvedViewPath = prefix + viewName + suffix;
        log.info("view resolved to '{}'", resolvedViewPath);
        return resolvedViewPath;
    }

}
