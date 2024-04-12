package dev.jihogrammer.member.adaptor.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.Map;

@Slf4j
@Component("/legacy/home")
public class OldHomeController implements Controller {

    @Override
    public ModelAndView handleRequest(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response
    ) {
        final var model = Map.of("controller", this.getClass().getSimpleName());

        return new ModelAndView("index", model);
    }

}
