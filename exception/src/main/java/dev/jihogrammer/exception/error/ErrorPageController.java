package dev.jihogrammer.exception.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.RequestDispatcher.*;

/**
 * WebServerCustomizer 설정은 스프링 내부 기본 설정을 따르게 되면서 더 이상 필요 없어짐
 * @see dev.jihogrammer.exception.config.WebServerCustomizer
 */
@Controller
@Slf4j
public class ErrorPageController {
    public static final String ERROR_URI_404 = "/error/404";
    public static final String ERROR_URI_500 = "/error/500";

    @RequestMapping(ERROR_URI_404)
    public String errorPage404(final HttpServletRequest request) {
        logErrorDetail(request);
        return "/error-page/404";
    }

    @RequestMapping(ERROR_URI_500)
    public String errorPage500(final HttpServletRequest request) {
        logErrorDetail(request);
        return "/error-page/500";
    }

    @RequestMapping(value = ERROR_URI_500, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> error500JSON(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) {
        logErrorDetail(request);

        Map<String, Object> result = new HashMap<>();

        result.put("status", request.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ((Exception) request.getAttribute(ERROR_EXCEPTION)).getMessage());

        return new ResponseEntity<>(result, HttpStatusCode.valueOf((int) result.get("status")));
    }

    private void logErrorDetail(final HttpServletRequest request) {
        log.info("ERROR_EXCEPTION: {}", request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE: {}", request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatcherType: {}", request.getDispatcherType());
    }
}
