package dev.jihogrammer.spring.exception.servlet;

import dev.jihogrammer.spring.exception.model.UserException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * WAS(tomcat)까지 예외를 전파
 */
@Controller
@Slf4j
public class ServletExceptionController {

    @RequestMapping("/exception/example")
    public void errorExample() {
        log.error("errorExample");
        throw new RuntimeException("error example");
    }

    @RequestMapping("/exception/user")
    public void userException() {
        log.error("errorExample");
        throw new UserException("error.bad");
    }

    @RequestMapping("/exception/404")
    public void error404(final HttpServletResponse response) throws IOException {
        log.error("error404");
        response.sendError(404);
    }

    @RequestMapping("/exception/500")
    public void error500(final HttpServletResponse response) throws IOException {
        log.error("error500");
        response.sendError(500);
    }

}
