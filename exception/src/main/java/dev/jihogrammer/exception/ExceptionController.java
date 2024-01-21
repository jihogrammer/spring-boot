package dev.jihogrammer.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@Slf4j
public class ExceptionController {
    @RequestMapping("/exception/example")
    public void errorExample() {
        log.error("errorExample");
        throw new RuntimeException("error example");
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
