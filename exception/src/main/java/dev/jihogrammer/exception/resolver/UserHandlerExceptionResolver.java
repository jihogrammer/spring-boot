package dev.jihogrammer.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.exception.model.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final String UTF_8 = "utf-8";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        final Object handler,
        @NonNull final Exception ex
    ) {
        try {
            if (UserException.class.isAssignableFrom(ex.getClass())) {
                log.info("resolve UserException to 400 status");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if (MediaType.TEXT_HTML_VALUE.equals(request.getHeader("accept"))) {
                    return new ModelAndView("/error/400");
                } else {
                    Map<String, Object> result = new HashMap<>();
                    result.put("exception", ex.getClass());
                    result.put("message", ex.getMessage());

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setCharacterEncoding(UTF_8);
                    response.getWriter().print(objectMapper.writeValueAsString(result));

                    return new ModelAndView();
                }
            }
        } catch (IOException e) {
            log.error("resolver exception", e);
        }
        return null;
    }
}
