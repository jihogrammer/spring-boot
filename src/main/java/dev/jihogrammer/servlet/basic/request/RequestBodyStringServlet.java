package dev.jihogrammer.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(urlPatterns = RequestBodyStringServlet.URI)
@Slf4j
public class RequestBodyStringServlet extends HttpServlet {
    public static final String URI = "/request-body-string";

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestBody = StreamUtils.copyToString(request.getInputStream(), UTF_8);
        log.info("request in: {}", requestBody);
        response.getWriter().write("hello world: " + requestBody);
    }
}
