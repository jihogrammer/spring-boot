package dev.jihogrammer.servlet.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ViewResolver {

    private final String prefix;

    private final String suffix;

    public String resolve(final String viewName) {
        var resolvedViewPath = prefix + viewName + suffix;
        log.info("resolved '{}'", resolvedViewPath);
        return resolvedViewPath;
    }

}
