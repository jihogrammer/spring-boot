package dev.jihogrammer.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(urlPatterns = "request-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestBody = StreamUtils.copyToString(request.getInputStream(), UTF_8);
        response.getWriter().write("hello world: " + requestBody);
    }
}
