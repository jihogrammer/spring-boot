package dev.jihogrammer.exception.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ErrorPageController {
    public static final String ERROR_URI_404 = "/error/404";
    public static final String ERROR_URI_500 = "/error/500";

    @RequestMapping(ERROR_URI_404)
    public String errorPage404(final HttpServletRequest request, final HttpServletResponse response) {
        log.error("error page 404");
        logErrorDetail(request);
        return "/error-page/404";
    }

    @RequestMapping(ERROR_URI_500)
    public String errorPage500(final HttpServletRequest request, final HttpServletResponse response) {
        log.error("error page 500");
        logErrorDetail(request);
        return "/error-page/500";
    }

    private void logErrorDetail(final HttpServletRequest request) {
        log.info("ERROR_EXCEPTION: {}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE: {}", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        log.info("dispatcherType: {}", request.getDispatcherType());
    }
}
