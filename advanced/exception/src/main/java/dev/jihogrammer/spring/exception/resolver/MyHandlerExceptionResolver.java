package dev.jihogrammer.spring.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        final Object handler,
        @NonNull final Exception ex
    ) {
        try {
            if (IllegalArgumentException.class.isAssignableFrom(ex.getClass())) {
                log.info("resolve IllegalArgumentException to 400 status");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver exception", e);
        }
        return null;
    }
}
