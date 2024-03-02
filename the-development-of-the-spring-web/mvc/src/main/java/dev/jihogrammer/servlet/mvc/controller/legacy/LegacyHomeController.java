package dev.jihogrammer.servlet.mvc.controller.legacy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.Map;

@Slf4j
@Component(value = LegacyHomeController.URI)
public class LegacyHomeController implements Controller {

    public static final String URI = "/legacy/home";

    private final String page;

    public LegacyHomeController(@Value("${page.index}") final String page) {
        this.page = page;
    }

    @Override
    public ModelAndView handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
    ) {
        log.info("REQUEST HOME");
        return new ModelAndView(this.page, Map.of("controller", this.getClass().getSimpleName()));
    }

}
