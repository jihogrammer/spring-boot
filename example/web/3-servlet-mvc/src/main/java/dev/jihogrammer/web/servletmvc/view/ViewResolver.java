package dev.jihogrammer.web.servletmvc.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ViewResolver {

    private final String prefix;

    private final String suffix;

    private final String getViewName;

    private final String postViewName;

    private String resolve(final String viewName) {
        return this.prefix + viewName + this.suffix;
    }

    public String resolveGetView() {
        return this.resolve(this.getViewName);
    }

    public String resolvePostView() {
        return this.resolve(this.postViewName);
    }

}
