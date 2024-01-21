package dev.jihogrammer.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ExceptionController {
    @GetMapping("/exception/example")
    public void errorExample() {
        throw new RuntimeException("error example");
    }

    @GetMapping("/exception/404")
    public void error404(final HttpServletResponse response) throws IOException {
        response.sendError(404);
    }

    @GetMapping("/exception/500")
    public void error500(final HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
}
