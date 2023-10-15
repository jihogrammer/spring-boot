package dev.jihogrammer.spring_mvc.legacy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.IOException;

@Component(value = "/legacy/controller")
public class LegacyController implements Controller {
    @Override
    public ModelAndView handleRequest(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response
    ) throws IOException {
        response.getWriter().write(this.getClass().getSimpleName());
        return null;
    }
}
