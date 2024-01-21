package dev.jihogrammer.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ErrorPageController {
    @RequestMapping("/error-page/404")
    public String errorPage404(final HttpServletRequest request, final HttpServletResponse response) {
        log.error("error page 404");
        return ErrorPagePath.NOT_FOUND;
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(final HttpServletRequest request, final HttpServletResponse response) {
        log.error("error page 500");
        return ErrorPagePath.INTERNAL_SERVER_ERROR;
    }
}
